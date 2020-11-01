package com.jyami.commitersewha.config.restTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
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

    //TODO : accessToken 추가
    public JsonNode getUserRepositories(String accessToken, int page) {
        log.info("search user repository");
        String requestUrl = UriComponentsBuilder.fromUriString("/user/repos")
                .queryParam("page", page)
                .build().toUriString();
        return restTemplate.getForObject(requestUrl, JsonNode.class);
    }

    public JsonNode getUserDetailInfo(String accessToken) {
        log.info("get user info");
        return restTemplate.getForObject("/user", JsonNode.class);
    }

    public JsonNode getCommitList(){

    }

}
