package com.jyami.commitersewha.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.map;
import static com.querydsl.core.types.Projections.list;

/**
 * Created by jyami on 2020/10/01
 */
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // TODO : 댓글 페이징 안 되어있음.
    @Override
    public Optional<Post> findPostByIdWithComments(long postId) {
        Post post = jpaQueryFactory.selectFrom(QPost.post)
                .leftJoin(QPost.post.comments, QComment.comment).fetchJoin()
                .leftJoin(QPost.post.user, QUser.user).fetchJoin()
                .where(QPost.post.postId.eq(postId))
                .fetchFirst();
        return Optional.ofNullable(post);
    }

    @Override
    public List<Comment> findPostByIdWithComments2(long postId) {
        QComment comment = QComment.comment;
        return jpaQueryFactory.selectFrom(comment)
                .rightJoin(comment.post, QPost.post).fetchJoin()
                .leftJoin(comment.post.user, QUser.user).fetchJoin()
                .where(QPost.post.postId.eq(postId))
                .fetch();
    }
}
