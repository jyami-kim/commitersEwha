package com.jyami.commitersewha.service;

import com.jyami.commitersewha.githubRestTemplate.response.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;


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

class TimeTest{
    @Test
    void name() {
        LocalDateTime standard = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime commitTime = LocalDateTime.of(2019, 12, 1, 0, 0);
        boolean dateStandard = standard.isBefore(commitTime);
        System.out.println(dateStandard);
    }
}