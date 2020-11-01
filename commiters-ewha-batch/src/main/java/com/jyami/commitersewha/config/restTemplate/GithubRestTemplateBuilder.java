package com.jyami.commitersewha.config.restTemplate;

import com.google.common.net.HttpHeaders;
import com.jyami.commitersewha.config.GithubProperties;
import com.jyami.commitersewha.config.handler.RestTemplateErrorHandler;
import org.springframework.web.client.RestTemplate;


class GithubRestTemplateBuilder {

    private GithubRestTemplateBuilder() {
    }

    static RestTemplate getGithubDefaultSetting(GithubProperties properties) {
        return new org.springframework.boot.web.client.RestTemplateBuilder()
                .setConnectTimeout(properties.getConnectionTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .rootUri(properties.getHost())
                .defaultHeader(HttpHeaders.ACCEPT, "*/*")
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

}
