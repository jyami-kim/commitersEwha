package com.jyami.commitersewha.service;

import com.jyami.commitersewha.githubRestTemplate.response.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/11/09
 */
@SpringBootTest
@ActiveProfiles("test")
class GithubInfoServiceTest {

    @Autowired
    private GithubInfoService githubInfoService;

    @Value("${github.token}")
    private String accessToken;

    @Test
    void getAllRepository() {
        List<RepositoryResponse> allRepository = githubInfoService.getAllRepository(accessToken);
        System.out.println(allRepository.size());
    }
}