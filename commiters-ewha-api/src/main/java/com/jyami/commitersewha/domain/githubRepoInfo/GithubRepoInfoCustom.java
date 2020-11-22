package com.jyami.commitersewha.domain.githubRepoInfo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/12
 */
public interface GithubRepoInfoCustom {
    List<GithubRepoInfo> findBetweenTime(LocalDateTime startTime, LocalDateTime endTime, Long githubRepoInfoId);
}
