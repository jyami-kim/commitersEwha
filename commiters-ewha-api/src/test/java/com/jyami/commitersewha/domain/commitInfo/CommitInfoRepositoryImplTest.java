package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.preSetting.TestConfig;
import com.jyami.commitersewha.util.TimeUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jyami on 2020/11/12
 */
@DataJpaTest
@ActiveProfiles("test")
@Import(TestConfig.class)
class CommitInfoRepositoryImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CommitInfoRepository commitInfoRepository;

    @Autowired
    private GithubInfoRepository githubInfoRepository;

    @Autowired
    private UserRepository userRepository;

    private void sampleCommitSave() {

        User buildUser = User.builder()
                .email("mor2222@naver.com")
                .name("jyami")
                .role(User.Role.USER)
                .build();

        userRepository.save(buildUser);

        GithubInfo mjung1798 = GithubInfo.builder()
                .user(buildUser)
                .authorId("mjung1798").build();

        githubInfoRepository.save(mjung1798);

        GithubCommitInfo commitInfo = GithubCommitInfo.builder()
                .sha("aaa")
                .date(LocalDateTime.of(2020, 11, 11, 1, 1))
                .commitMessage("첫번째 커밋")
                .githubInfo(mjung1798)
                .build();

        GithubCommitInfo commitInfo2 = GithubCommitInfo.builder()
                .sha("bbb")
                .date(LocalDateTime.of(2020, 11, 12, 1, 1))
                .commitMessage("두번째 커밋")
                .githubInfo(mjung1798)
                .build();

        GithubCommitInfo commitInfo3 = GithubCommitInfo.builder()
                .sha("bbb")
                .date(LocalDateTime.of(2020, 11, 11, 12, 1))
                .commitMessage("세번째 커밋")
                .githubInfo(mjung1798)
                .build();

        commitInfoRepository.saveAll(Arrays.asList(commitInfo, commitInfo2, commitInfo3));
    }

    @Test
    void findBetweenTime() {
        sampleCommitSave();
        LocalDate date = LocalDate.of(2020, 11, 11);
        List<GithubCommitInfo> betweenTime = commitInfoRepository.findBetweenTime(TimeUtils.getStartDate(date), TimeUtils.getEndDate(date), 1L);
        System.out.println(betweenTime);
    }

    @Test
    @Disabled
    @DisplayName("commitMap 찾기 : 그런데 H2에서는 에러남")
    void findCommitMap() {
        sampleCommitSave();
        List<CommitMap> commitMapCount = commitInfoRepository.findCommitMapCount(TimeUtils.getThisYearStartTime(), TimeUtils.getTodayEndTime(), 1L);
        System.out.println(commitMapCount);
    }
}