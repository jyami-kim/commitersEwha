package com.jyami.commitersewha.config.restTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by jyami on 2020/11/01
 */
@Slf4j
public class GithubRestTemplate {

    private final RestTemplate restTemplate;

    public GithubRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<JsonNode> getUserRepositories(String accessToken, int page) {
        String requestUrl = UriComponentsBuilder.fromUriString("/user/repos")
                .queryParam("page", page)
                .queryParam("sort", "created")
                .build().toUriString();
        log.info("[REPOS] : {}", requestUrl);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken), JsonNode.class);
    }

    public ResponseEntity<JsonNode> getUserDetailInfo(String accessToken) {
        return restTemplate.exchange("/user", HttpMethod.GET, getEntityWithHeader(accessToken), JsonNode.class);
    }

    public ResponseEntity<JsonNode> getReposCommitStat(String accessToken, int page, String owner, String repo) {
        String requestUrl = UriComponentsBuilder.fromUriString("/repos")
                .path("/{owner}")
                .path("/{repo}")
                .path("/stats/contributors")
                .queryParam("page", page)
                .queryParam("sort", "created")
                .buildAndExpand(owner, repo).toUriString();
        log.info("[STAT] : {}", requestUrl);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken), JsonNode.class);
    }

    public ResponseEntity<JsonNode> getReposCommitList(String accessToken, int page, String owner, String repo, String author) {
        String requestUrl = UriComponentsBuilder.fromUriString("/repos")
                .path("/{owner}")
                .path("/{repo}")
                .path("/commits")
                .queryParam("page", page)
                .queryParam("author", author)
                .buildAndExpand(owner, repo).toUriString();
        log.info("[COMMIT] : {}", requestUrl);
        return restTemplate.exchange(requestUrl, HttpMethod.GET, getEntityWithHeader(accessToken), JsonNode.class);
    }

    private HttpEntity<String> getEntityWithHeader(String accessToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, accessToken);
        return new HttpEntity<>(headers);
    }
}
