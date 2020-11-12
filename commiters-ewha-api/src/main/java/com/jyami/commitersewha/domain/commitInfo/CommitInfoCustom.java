package com.jyami.commitersewha.domain.commitInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/12
 */
public interface CommitInfoCustom {
    List<GithubCommitInfo> findBetweenTime(LocalDateTime startTime, LocalDateTime endTime, Long githubInfoId);

}
