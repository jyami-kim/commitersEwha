package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.domain.comment.QComment;
import com.jyami.commitersewha.domain.user.QUser;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;


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

    @Override
    public Page<Post> findAllBySearchCondition(SearchRequest searchRequest) {
        PostSearchRequestParamMapper queryMapper = new PostSearchRequestParamMapper(searchRequest);
        JPAQuery<Post> searchQuery = queryMapper.getSearchConditionMapping(jpaQueryFactory.selectFrom(QPost.post));
        QueryResults<Post> postQueryResults = searchQuery.
                leftJoin(QPost.post.user, QUser.user).fetchJoin()
                .fetchResults();
        return new PageImpl<>(postQueryResults.getResults(), queryMapper.getPageRequest(), postQueryResults.getTotal());
    }

}
