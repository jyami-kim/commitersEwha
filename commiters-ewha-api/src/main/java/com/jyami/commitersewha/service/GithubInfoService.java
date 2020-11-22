package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.commitInfo.GithubCommitInfo;
import com.jyami.commitersewha.domain.commitInfo.CommitInfoRepository;
import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
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
import com.jyami.commitersewha.util.TimeUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

    public List<CommitMap> findCommitMapCount(String authorId) {
        GithubInfo githubInfo = githubInfoRepository.findByAuthorId(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "authorId", authorId));
        return commitInfoRepository.findCommitMapCount(TimeUtils.getThisYearStartTime(), TimeUtils.getTodayEndTime(), githubInfo.getId());
    }

    @Transactional
    public HashMap<String, List<GithubCommitInfo>> updateDateInfo(LocalDateTime startDate, Long userId) { // endDate는 무조껀 오늘 날짜
        GithubInfo githubInfo = githubInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "userId", userId));
        String token = githubInfo.getToken();
        List<GithubRepoInfo> updateRepositoryInfo = updateDateRepository(token, startDate, githubInfo);
        return updateCommitInfo(token, updateRepositoryInfo, githubInfo, startDate);
    }

    protected List<GithubRepoInfo> updateDateRepository(String token, LocalDateTime startDate, GithubInfo githubInfo) {

        List<GithubRepoInfo> collect = validateStatusAndGetBody(githubRestTemplate.getUserRepositories(token, 1)).stream()
                .filter(x -> x.getUpdatedAt().isAfter(startDate))
                .map(x -> x.toEntity(githubInfo))
                .collect(Collectors.toList());

        return githubRepoInfoRepository.saveAll(collect);
    }

    protected HashMap<String, List<GithubCommitInfo>> updateCommitInfo(String token, List<GithubRepoInfo> githubRepoInfos, GithubInfo githubInfo, LocalDateTime startDate) {

        List<GithubCommitInfo> betweenTime = commitInfoRepository.findBetweenTime(startDate, TimeUtils.getTodayEndTime(), githubInfo.getInfoId());

        List<GithubCommitInfo> githubCommitInfos = githubRepoInfos.stream()
                .map(c -> getUpdatedCommits(token, c, githubInfo.getAuthorId(), startDate))
                .filter(c -> !c.isEmpty())
                .flatMap(x -> x.stream()
                        .map(y -> y.toEntity(githubInfo)))
                .collect(Collectors.toList());

        List<GithubCommitInfo> saveData = commitInfoRepository.saveAll(githubCommitInfos);

        List<GithubCommitInfo> removeData = betweenTime.stream()
                .filter(x -> !saveData.contains(betweenTime))
                .collect(Collectors.toList());

        commitInfoRepository.deleteAll(removeData);

        return new HashMap<String, List<GithubCommitInfo>>() {{
            put("saveData", saveData);
            put("deleteData", removeData);
        }};
    }

    protected List<GithubCommitResponse> getUpdatedCommits(String token, GithubRepoInfo githubRepoInfo, String authorId, LocalDateTime startDate) {
        int page = 1;
        List<GithubCommitResponse> repositoryResponses = new ArrayList<>();
        while (true) {
            try {
                ResponseEntity<List<GithubCommitResponse>> commitList = githubRestTemplate
                        .getReposCommitList(token, page++, githubRepoInfo.getOwner(), githubRepoInfo.getName(), authorId);

                List<GithubCommitResponse> commitResponses = validateStatusAndGetBody(commitList).stream()
                        .filter(x -> x.getCommit().getAuthor().getDate().isAfter(startDate))
                        .collect(Collectors.toList());

                boolean addAll = repositoryResponses.addAll(commitResponses);
                if (!addAll) {
                    break;
                }
            } catch (RestTemplateResponseException e) {
                if (e.getStatus() != 409) {
                    log.error(e.getLocalizedMessage());
                }
                break;
            }
        }
        return repositoryResponses;
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

    protected List<GithubCommitResponse> getAllCommits(String token, GithubRepoInfo githubRepoInfo, String authorId) {
        int page = 1;
        List<GithubCommitResponse> repositoryResponses = new ArrayList<>();
        while (true) {
            try {
                ResponseEntity<List<GithubCommitResponse>> commitList = githubRestTemplate
                        .getReposCommitList(token, page++, githubRepoInfo.getOwner(), githubRepoInfo.getName(), authorId);
                List<GithubCommitResponse> commitResponses = validateStatusAndGetBody(commitList);
                if (commitResponses.size() == 0) {
                    break;
                }
                boolean dateStandard = DATE_STANDARD.isAfter(commitResponses.get(0).getCommit().getAuthor().getDate());
                if (dateStandard) {
                    log.info(commitResponses.get(0).getCommit().getAuthor().getDate().toString());
                    break;
                }
                repositoryResponses.addAll(commitResponses);
            } catch (RestTemplateResponseException e) {
                if (e.getStatus() != 409) {
                    log.error(e.getLocalizedMessage());
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
