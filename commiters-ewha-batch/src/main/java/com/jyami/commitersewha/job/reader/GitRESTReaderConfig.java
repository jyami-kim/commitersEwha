package com.jyami.commitersewha.job.reader;

import com.jyami.commitersewha.config.payload.RepositoryResponse;
import com.jyami.commitersewha.config.restTemplate.GithubRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by jyami on 2020/11/07
 */
@Configuration
@RequiredArgsConstructor
public class GitRESTReaderConfig {

    private final GithubRestTemplate githubRestTemplate;

    @Value("#{jobParameters[userToken]}")
    private String userToken;

    @Bean
    ItemReader<List<RepositoryResponse>> restRepositoryReader(){
        return new GitRESTRepositoryReader(githubRestTemplate, userToken);
    }

}
