package com.jyami.commitersewha.domain.comment;

import com.jyami.commitersewha.domain.post.QPost;
import com.jyami.commitersewha.domain.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by jyami on 2020/10/02
 */
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Comment> findPostByIdWithComments(long postId) {
        return jpaQueryFactory.selectFrom(QComment.comment)
                .join(QComment.comment.post, QPost.post).fetchJoin()
                .leftJoin(QComment.comment.user, QUser.user).fetchJoin()
                .leftJoin(QComment.comment.likesUser, QUser.user).fetchJoin()
                .where(QPost.post.postId.eq(postId))
                .fetch();
    }

}
