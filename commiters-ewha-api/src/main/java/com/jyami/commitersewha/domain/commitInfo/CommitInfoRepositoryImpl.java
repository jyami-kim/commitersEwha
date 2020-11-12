package com.jyami.commitersewha.domain.commitInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/12
 */
@RequiredArgsConstructor
public class CommitInfoRepositoryImpl implements CommitInfoCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GithubCommitInfo> findBetweenTime(LocalDateTime startTime, LocalDateTime endTime, Long githubInfoId) {
        return jpaQueryFactory.selectFrom(QGithubCommitInfo.githubCommitInfo)
                .where(QGithubCommitInfo.githubCommitInfo.date.between(startTime, endTime))
                .fetch();
    }
}
