package com.jyami.commitersewha.githubRestTemplate;

import com.google.common.net.HttpHeaders;
import com.jyami.commitersewha.config.GithubProperties;
import org.springframework.web.client.RestTemplate;


class GithubRestTemplateBuilder {

    private GithubRestTemplateBuilder() {
    }

    static RestTemplate getGithubDefaultSetting(GithubProperties properties) {
        return new org.springframework.boot.web.client.RestTemplateBuilder()
                .setConnectTimeout(properties.getConnectionTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .rootUri(properties.getHost())
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

}
