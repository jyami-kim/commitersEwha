package com.jyami.commitersewha.config.restTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/11/02
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class GithubRestTemplateTest {

    @Autowired
    private GithubRestTemplate githubRestTemplate;

    @Value("${github.token}")
    private String accessToken;

    @Test
    void getUserRepositories() {
        ResponseEntity<JsonNode> userRepositories = githubRestTemplate.getUserRepositories(accessToken, 1);
        System.out.println(userRepositories);
    }

    @Test
    void getUserDetailInfo() {
        ResponseEntity<JsonNode> userRepositories = githubRestTemplate.getUserDetailInfo(accessToken);
        System.out.println(userRepositories);
    }

    @Test
    void getReposCommitStat() {
        ResponseEntity<JsonNode> userRepositories = githubRestTemplate.getReposCommitStat(accessToken, 1, "java-Bom", "ReadingRecord");
        System.out.println(userRepositories);
    }

    @Test
    void getReposCommitList() {
        ResponseEntity<JsonNode> userRepositories = githubRestTemplate.getReposCommitList(accessToken, 1, "java-Bom", "ReadingRecord", "mjung1798");
        System.out.println(userRepositories);
    }
}