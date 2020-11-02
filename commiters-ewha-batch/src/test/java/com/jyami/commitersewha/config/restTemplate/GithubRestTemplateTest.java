package com.jyami.commitersewha.config.restTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.jyami.commitersewha.config.payload.CommitResponse;
import com.jyami.commitersewha.config.payload.CommitStatisticResponse;
import com.jyami.commitersewha.config.payload.RepositoryResponse;
import com.jyami.commitersewha.config.payload.UserDetailResponse;
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

import java.util.List;
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
        ResponseEntity<List<RepositoryResponse>> userRepositories = githubRestTemplate.getUserRepositories(accessToken, 1);
        userRepositories.getBody().forEach(System.out::println);
    }

    @Test
    void getUserDetailInfo() {
        ResponseEntity<UserDetailResponse> userDetailInfo = githubRestTemplate.getUserDetailInfo(accessToken);
        System.out.println(userDetailInfo.getBody());
    }

    @Test
    void getReposCommitStat() {
        ResponseEntity<List<CommitStatisticResponse>> reposCommitStat = githubRestTemplate.getReposCommitStat(accessToken, 1, "java-Bom", "ReadingRecord");
        reposCommitStat.getBody().forEach(System.out::println);
    }

    @Test
    void getReposCommitList() {
        ResponseEntity<List<CommitResponse>> reposCommitList = githubRestTemplate.getReposCommitList(accessToken, 1, "java-Bom", "ReadingRecord", "mjung1798");
        reposCommitList.getBody().forEach(System.out::println);
    }
}