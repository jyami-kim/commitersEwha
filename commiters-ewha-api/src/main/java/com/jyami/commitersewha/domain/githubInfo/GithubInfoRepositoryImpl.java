package com.jyami.commitersewha.domain.githubInfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Created by jyami on 2020/11/08
 */
@RequiredArgsConstructor
public class GithubInfoRepositoryImpl implements GithubInfoCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<GithubInfo> findByUserId(Long userId) {
        GithubInfo githubInfo = jpaQueryFactory.selectFrom(QGithubInfo.githubInfo)
                .where(QGithubInfo.githubInfo.user.userId.eq(userId))
                .fetchOne();
        return Optional.ofNullable(githubInfo);
    }
}
