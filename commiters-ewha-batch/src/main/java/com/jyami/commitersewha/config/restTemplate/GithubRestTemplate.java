package com.jyami.commitersewha.config.restTemplate;

import com.jyami.commitersewha.config.payload.CommitResponse;
import com.jyami.commitersewha.config.payload.CommitStatisticResponse;
import com.jyami.commitersewha.config.payload.RepositoryResponse;
import com.jyami.commitersewha.config.payload.UserDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by jyami on 2020/11/01
 */
@Slf4j
public class GithubRestTemplate {

    private final RestTemplate restTemplate;

    public GithubRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<RepositoryResponse>> getUserRepositories(String accessToken, int page) {
        String requestUrl = UriComponentsBuilder.fromUriString("/user/repos")
                .queryParam("page", page)
                .queryParam("sort", "created")
                .build().toUriString();
        log.info("[REPOS] : {}", requestUrl);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken),
                new ParameterizedTypeReference<List<RepositoryResponse>>(){});
    }

    public ResponseEntity<UserDetailResponse> getUserDetailInfo(String accessToken) {
        return restTemplate.exchange("/user", HttpMethod.GET, getEntityWithHeader(accessToken), UserDetailResponse.class);
    }

    public ResponseEntity<List<CommitStatisticResponse>> getReposCommitStat(String accessToken, int page, String owner, String repo) {
        String requestUrl = UriComponentsBuilder.fromUriString("/repos")
                .path("/{owner}")
                .path("/{repo}")
                .path("/stats/contributors")
                .queryParam("page", page)
                .queryParam("sort", "updated")
                .buildAndExpand(owner, repo).toUriString();
        log.info("[STAT] : {}", requestUrl);

        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken),
                new ParameterizedTypeReference<List<CommitStatisticResponse>>(){});
    }

    public ResponseEntity<List<CommitResponse>> getReposCommitList(String accessToken, int page, String owner, String repo, String author) {
        String requestUrl = UriComponentsBuilder.fromUriString("/repos")
                .path("/{owner}")
                .path("/{repo}")
                .path("/commits")
                .queryParam("page", page)
                .queryParam("author", author)
                .buildAndExpand(owner, repo).toUriString();
        log.info("[COMMIT] : {}", requestUrl);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken),
                new ParameterizedTypeReference<List<CommitResponse>>(){});
    }

    private HttpEntity<String> getEntityWithHeader(String accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, accessToken);
        return new HttpEntity<>(headers);
    }
}
