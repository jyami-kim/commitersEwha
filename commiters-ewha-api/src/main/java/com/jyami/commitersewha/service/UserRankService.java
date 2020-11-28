package com.jyami.commitersewha.service;

import com.jyami.commitersewha.domain.commitInfo.CommitInfoRepository;
import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.domain.userRank.UserRank;
import com.jyami.commitersewha.domain.userRank.UserRankRepository;
import com.jyami.commitersewha.domain.userRank.dto.CommitMapRank;
import com.jyami.commitersewha.exception.ResourceNotFoundException;
import com.jyami.commitersewha.payload.response.UserRankInfo;
import com.jyami.commitersewha.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/28
 */
@Service
@RequiredArgsConstructor
public class UserRankService {

    private final UserRankRepository userRankRepository;
    private final GithubInfoRepository githubInfoRepository;
    private final CommitInfoRepository commitInfoRepository;

    @Transactional
    public void saveRank(Long userId) {
        GithubInfo githubInfo = githubInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "userId", userId));
        saveRankAsQuarter(githubInfo);
        saveRankAsWeek(githubInfo);
    }

    public UserRankInfo getUserRank(String authorId) {
        GithubInfo githubInfo = githubInfoRepository.findByAuthorId(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("GithubInfo", "authorId", authorId));
        LocalDateTime thisWeekStartTime = TimeUtils.getThisWeekStartTime(LocalDate.now());
        UserRank userRank = userRankRepository.findByGithubInfoAndWeekAndLocalDate(githubInfo, true, thisWeekStartTime.toLocalDate())
                .orElseGet(() -> UserRank.emptyUserRank(githubInfo));
        return UserRankInfo.of(userRank, githubInfo);
    }

    private void saveRankAsWeek(GithubInfo githubInfo) {
        LocalDateTime thisWeekStartTime = TimeUtils.getThisWeekStartTime(LocalDate.now());
        List<CommitMap> commitWeek = findCommitMapStartTime(githubInfo.getInfoId(), thisWeekStartTime);
        UserRank userRank = CommitMapRank.calculate(commitWeek).of(thisWeekStartTime.toLocalDate(), true, githubInfo);
        userRankRepository.save(userRank);
    }

    private void saveRankAsQuarter(GithubInfo githubInfo) {
        LocalDateTime thisQuarterStartTime = TimeUtils.getThisQuarterStartTime(LocalDate.now());
        List<CommitMap> commitQuarter = findCommitMapStartTime(githubInfo.getInfoId(), thisQuarterStartTime);
        UserRank userRank = CommitMapRank.calculate(commitQuarter).of(thisQuarterStartTime.toLocalDate(), false, githubInfo);
        userRankRepository.save(userRank);
    }

    private List<CommitMap> findCommitMapStartTime(Long githubInfoId, LocalDateTime startTime) {
        return commitInfoRepository.findCommitMapCount(startTime, TimeUtils.getTodayEndTime(), githubInfoId);
    }

}
