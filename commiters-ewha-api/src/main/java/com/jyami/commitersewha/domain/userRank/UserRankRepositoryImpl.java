package com.jyami.commitersewha.domain.userRank;

import com.jyami.commitersewha.domain.githubInfo.QGithubInfo;
import com.jyami.commitersewha.domain.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by jyami on 2020/11/28
 */
@RequiredArgsConstructor
public class UserRankRepositoryImpl implements UserRankRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserRank> findAllUserRankings(boolean week, LocalDate localDate) {
        return jpaQueryFactory.selectFrom(QUserRank.userRank)
                .where(QUserRank.userRank.week.eq(week))
                .where(QUserRank.userRank.localDate.eq(localDate))
                .join(QUserRank.userRank.githubInfo, QGithubInfo.githubInfo).fetchJoin()
                .join(QGithubInfo.githubInfo.user, QUser.user).fetchJoin()
                .orderBy(QUserRank.userRank.score.desc())
                .fetch();
    }
}
