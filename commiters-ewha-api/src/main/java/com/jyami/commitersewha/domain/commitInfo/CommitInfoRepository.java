package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/09
 */
public interface CommitInfoRepository extends JpaRepository<GithubCommitInfo, Long>, CommitInfoCustom {
    @Query(value =
            "SELECT FUNCTION('date_format', com.date, '%Y-%m-%d') AS commitDate, " +
                    "COUNT(com.sha) AS commitCount " +
                    "FROM GithubCommitInfo com " +
                    "WHERE com.date BETWEEN ?1 AND ?2 " +
                    "AND com.infoId = ?3 " +
                    "GROUP BY FUNCTION('date_format', com.date, '%Y-%m-%d') "
            , nativeQuery = true
    )
    List<CommitMap> findCommitMapCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId);
}
