package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.commitInfo.dto.CommitMap;
import com.jyami.commitersewha.domain.githubRepoInfo.GithubRepoInfo;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
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
                .where(QGithubCommitInfo.githubCommitInfo.githubInfo.id.eq(githubInfoId))
                .fetch();
    }

}
