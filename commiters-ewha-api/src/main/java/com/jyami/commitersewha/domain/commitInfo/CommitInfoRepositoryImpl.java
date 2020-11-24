package com.jyami.commitersewha.domain.commitInfo;

import com.jyami.commitersewha.domain.commitInfo.dto.*;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyami on 2020/11/12
 */
@RequiredArgsConstructor
public class CommitInfoRepositoryImpl implements CommitInfoCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional(readOnly = true)
    public List<GithubCommitInfo> findBetweenTime(LocalDateTime startTime, LocalDateTime endTime, Long githubInfoId) {
        return jpaQueryFactory.selectFrom(QGithubCommitInfo.githubCommitInfo)
                .where(QGithubCommitInfo.githubCommitInfo.date.between(startTime, endTime))
                .where(QGithubCommitInfo.githubCommitInfo.githubInfo.id.eq(githubInfoId))
                .fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommitMap> findCommitMapCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId) {
        StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , QGithubCommitInfo.githubCommitInfo.date
                , ConstantImpl.create("%Y-%m-%d"));

        return jpaQueryFactory
                .select(new QCommitMap(QGithubCommitInfo.githubCommitInfo.sha.count().as("commitCount"), formattedDate.as("yyyymmdd")))
                .from(QGithubCommitInfo.githubCommitInfo)
                .where(
                        QGithubCommitInfo.githubCommitInfo.date.goe(startDate)
                        , QGithubCommitInfo.githubCommitInfo.date.loe(endDate)
                )
                .where(QGithubCommitInfo.githubCommitInfo.githubInfo.id.eq(githubInfoId))
                .groupBy(formattedDate)
                .orderBy(formattedDate.desc())
                .fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HourStat> findHourStatCommitCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId) {

        NumberExpression<Integer> formattedHour = Expressions.stringTemplate(
                "HOUR({0})",
                QGithubCommitInfo.githubCommitInfo.date).castToNum(Integer.class);

        return jpaQueryFactory.select(
                new QHourStat(QGithubCommitInfo.githubCommitInfo.sha.count().as("count"), formattedHour.as("hour")))
                .from(QGithubCommitInfo.githubCommitInfo)
                .where(QGithubCommitInfo.githubCommitInfo.date.goe(startDate)
                        .and(QGithubCommitInfo.githubCommitInfo.date.loe(endDate)))
                .groupBy(formattedHour)
                .orderBy(formattedHour.asc())
                .fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeekDayStat> findWeekdayStatCommitCount(LocalDateTime startDate, LocalDateTime endDate, Long githubInfoId) {

        NumberExpression<Integer> formattedHour = Expressions.stringTemplate(
                "WEEKDAY({0})",
                QGithubCommitInfo.githubCommitInfo.date).castToNum(Integer.class);

        return jpaQueryFactory.select(
                new QWeekDayStat(QGithubCommitInfo.githubCommitInfo.sha.count().as("count"), formattedHour.as("hour")))
                .from(QGithubCommitInfo.githubCommitInfo)
                .where(QGithubCommitInfo.githubCommitInfo.date.goe(startDate)
                        .and(QGithubCommitInfo.githubCommitInfo.date.loe(endDate)))
                .groupBy(formattedHour)
                .orderBy(formattedHour.asc())
                .fetch();
    }

}
