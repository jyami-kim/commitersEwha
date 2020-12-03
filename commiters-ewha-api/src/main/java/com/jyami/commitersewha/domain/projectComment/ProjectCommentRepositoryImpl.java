package com.jyami.commitersewha.domain.projectComment;

import com.jyami.commitersewha.domain.post.QPost;
import com.jyami.commitersewha.domain.projectPost.QProjectPost;
import com.jyami.commitersewha.domain.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by jyami on 2020/10/02
 */
@RequiredArgsConstructor
public class ProjectCommentRepositoryImpl implements ProjectCommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProjectComment> findPostByIdWithComments(long postId) {
        return jpaQueryFactory.selectFrom(QProjectComment.projectComment)
                .join(QProjectComment.projectComment.projectPost, QProjectPost.projectPost).fetchJoin()
                .leftJoin(QProjectComment.projectComment.user, QUser.user).fetchJoin()
                .leftJoin(QProjectComment.projectComment.likesUser, QUser.user).fetchJoin()
                .where(QPost.post.postId.eq(postId))
                .fetch();
    }

}
