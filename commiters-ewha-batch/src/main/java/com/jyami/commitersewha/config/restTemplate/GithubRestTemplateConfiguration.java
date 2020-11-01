package com.jyami.commitersewha.config.restTemplate;

import com.jyami.commitersewha.config.GithubProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GithubRestTemplateConfiguration {

    private final GithubProperties githubProperties;

    @Bean
    public GithubRestTemplate shoppingSearchRestTemplate() {
        log.debug("[GITHUB] restTemplate builder create bean.");
        RestTemplate shoppingSearch = GithubRestTemplateBuilder.getGithubDefaultSetting(githubProperties);
        return new GithubRestTemplate(shoppingSearch);
    }

}
