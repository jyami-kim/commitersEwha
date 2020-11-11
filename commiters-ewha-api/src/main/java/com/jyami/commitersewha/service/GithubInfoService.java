package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.commitInfo.GithubCommitInfo;
import com.jyami.commitersewha.domain.commitInfo.CommitInfoRepository;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.domain.githubRepoInfo.GithubRepoInfo;
import com.jyami.commitersewha.domain.githubRepoInfo.GithubRepoInfoRepository;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.exception.RestTemplateResponseException;
import com.jyami.commitersewha.githubRestTemplate.GithubRestTemplate;
import com.jyami.commitersewha.githubRestTemplate.response.CommitStatisticResponse;
import com.jyami.commitersewha.githubRestTemplate.response.GithubCommitResponse;
import com.jyami.commitersewha.githubRestTemplate.response.RepositoryResponse;
import com.jyami.commitersewha.payload.response.GithubDetailInfoResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jyami.commitersewha.payload.ResponseMessage.GITHUB_REST_CALL_ERROR;

/**
 * Created by jyami on 2020/11/08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GithubInfoService {

    private final static LocalDateTime DATE_STANDARD = LocalDateTime.of(2020, 1, 1, 0, 0);

    private final GithubInfoRepository githubInfoRepository;
    private final GithubRepoInfoRepository githubRepoInfoRepository;
    private final CommitInfoRepository commitInfoRepository;
    private final GithubRestTemplate githubRestTemplate;

    public GithubDetailInfoResponse getGithubInfoFromUserId(String authorId) {
        GithubInfo githubInfo = githubInfoRepository.findByAuthorId(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "authorId", authorId));
        return GithubDetailInfoResponse.fromEntity(githubInfo);
    }

    @Transactional
    public void saveNewCommitersInfo(Long userId) {
        GithubInfo githubInfo = githubInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "userId", userId));
        String token = githubInfo.getToken();
        List<GithubRepoInfo> repoInfos = saveRepositoryInfo(token, githubInfo);
        saveCommitInfo(token, repoInfos, githubInfo);
    }

    protected List<GithubRepoInfo> saveRepositoryInfo(String token, GithubInfo githubInfo) {
        List<GithubRepoInfo> repoInfos = getAllRepository(token).stream()
                .filter(x -> !x.isFork())
                .map(response -> response.toEntity(githubInfo))
                .collect(Collectors.toList());

        return githubRepoInfoRepository.saveAll(repoInfos);
    }

    protected List<RepositoryResponse> getAllRepository(String token) {
        int page = 1;
        List<RepositoryResponse> repositoryResponses = new ArrayList<>();
        while (true) {
            ResponseEntity<List<RepositoryResponse>> userRepositories = githubRestTemplate.getUserRepositories(token, page++);
            List<RepositoryResponse> repositoryData = validateStatusAndGetBody(userRepositories);
            boolean isAdd = repositoryResponses.addAll(repositoryData);
            if (!isAdd) {
                break;
            }
        }
        return repositoryResponses;
    }

    // addtion, deletion, commits를 가져온다. 일단 deprecated
    private CommitStatEntity saveRepositoryStats(String token, GithubRepoInfo githubRepoInfo, GithubInfo githubInfo) {
        ResponseEntity<List<CommitStatisticResponse>> reposCommitStat = githubRestTemplate.getReposCommitStat(token, githubRepoInfo.getOwner(), githubRepoInfo.getName());
        List<CommitStatisticResponse> statisticResponses = validateStatusAndGetBody(reposCommitStat);

        List<CommitStatisticResponse.Statistic> statistics = statisticResponses.stream()
                .filter(x -> x.getAuthor().getId().equals(githubInfo.getProviderId()))
                .findAny()
                .map(CommitStatisticResponse::getStatistics)
                .orElseGet(ArrayList::new);

        CommitStatEntity commitStatEntity = new CommitStatEntity();
        for (CommitStatisticResponse.Statistic stat : statistics) {
            commitStatEntity.additions += stat.getAdditions();
            commitStatEntity.deletions += stat.getDeletions();
            commitStatEntity.commits += stat.getCommits();
        }
        return commitStatEntity;
    }

    protected void saveCommitInfo(String token, List<GithubRepoInfo> githubRepoInfos, GithubInfo githubInfo) {
        List<GithubCommitInfo> githubCommitInfos = githubRepoInfos
                .stream()
                .map(c -> getAllCommits(token, c, githubInfo.getAuthorId()))
                .filter(c -> !c.isEmpty())
                .flatMap(x -> x.stream()
                        .map(y -> y.toEntity(githubInfo)))
                .collect(Collectors.toList());
        commitInfoRepository.saveAll(githubCommitInfos);
    }

    protected List<GithubCommitResponse> getAllCommits(String token, GithubRepoInfo githubRepoInfo, String authorId){
        int page = 1;
        List<GithubCommitResponse> repositoryResponses = new ArrayList<>();
        while (true) {
            try {
                ResponseEntity<List<GithubCommitResponse>> commitList = githubRestTemplate
                        .getReposCommitList(token, page++, githubRepoInfo.getOwner(), githubRepoInfo.getName(), authorId);
                List<GithubCommitResponse> commitResponses = validateStatusAndGetBody(commitList);
                boolean isAdd = repositoryResponses.addAll(commitResponses);

                if (!isAdd) {
                    break;
                }
                boolean dateStandard = DATE_STANDARD.isBefore(commitResponses.get(0).getCommit().getAuthor().getDate());
                if (dateStandard) {
                    break;
                }

            } catch (RestTemplateResponseException e) {
                if (e.getStatus() != 409) {
                    throw new RuntimeException(); // TODO CustomException
                }
                break;
            }
        }
        return repositoryResponses;
    }

    protected <T> List<T> validateStatusAndGetBody(ResponseEntity<List<T>> responseEntity) {
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RestTemplateResponseException(GITHUB_REST_CALL_ERROR);
        }
        return responseEntity.getBody();
    }

    @NoArgsConstructor
    static class CommitStatEntity {
        private long additions = 0L;
        private long deletions = 0L;
        private long commits = 0L;

        public GithubRepoInfo updateGithubRepoInfo(GithubRepoInfo githubRepoInfo) {
            githubRepoInfo.setAdditions(this.additions);
            githubRepoInfo.setDeletions(this.deletions);
            githubRepoInfo.setCommits(this.commits);
            return githubRepoInfo;
        }
    }

}
