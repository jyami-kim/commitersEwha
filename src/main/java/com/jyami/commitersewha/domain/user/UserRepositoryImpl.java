package com.jyami.commitersewha.domain.user;

import com.jyami.commitersewha.domain.tag.QBadge;
import com.jyami.commitersewha.domain.tag.QDevStack;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Created by jyami on 2020/10/03
 */
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findBySubId(String subId) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .leftJoin(QUser.user.badges, QBadge.badge).fetchJoin()
                .leftJoin(QUser.user.devStacks, QDevStack.devStack).fetchJoin()
                .where(QUser.user.subId.eq(subId))
                .fetchOne();
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUserId(Long userId) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .leftJoin(QUser.user.badges, QBadge.badge).fetchJoin()
                .leftJoin(QUser.user.devStacks, QDevStack.devStack).fetchJoin()
                .where(QUser.user.userId.eq(userId))
                .fetchOne();
        return Optional.ofNullable(user);
    }
}
