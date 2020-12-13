package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import com.jyami.commitersewha.domain.commitInfo.dto.HourStat;
import com.jyami.commitersewha.domain.commitInfo.dto.WeekDayStat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/12
 */
public interface CommitInfoCustom {
    List<GithubCommitInfo> findBetweenTime(LocalDateTime startTime, LocalDateTime endTime, Long githubInfoId);

    List<CommitMap> findCommitMapCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId);

    List<HourStat> findHourStatCommitCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId);

    List<WeekDayStat> findWeekdayStatCommitCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId);

    boolean checkExistCommit(Long githubInfoId);
}
