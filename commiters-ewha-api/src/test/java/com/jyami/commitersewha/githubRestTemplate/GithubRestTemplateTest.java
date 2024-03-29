package com.jyami.commitersewha.githubRestTemplate;

import com.jyami.commitersewha.githubRestTemplate.response.GithubCommitResponse;
import com.jyami.commitersewha.githubRestTemplate.response.CommitStatisticResponse;
import com.jyami.commitersewha.githubRestTemplate.response.RepositoryResponse;
import com.jyami.commitersewha.githubRestTemplate.response.UserDetailResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        // /repos/junctionxseoulzapzep/zepzap-server/stats/contributors
        ResponseEntity<List<CommitStatisticResponse>> reposCommitStat = githubRestTemplate.getReposCommitStat(accessToken, "junctionxseoulzapzep", "zepzap-server");
        reposCommitStat.getBody().forEach(System.out::println);
    }

    @Test
    void getReposCommitList() {
        ResponseEntity<List<GithubCommitResponse>> reposCommitList = githubRestTemplate.getReposCommitList(accessToken, 1, "java-Bom", "ReadingRecord", "mjung1798");
        reposCommitList.getBody().forEach(System.out::println);
    }
}