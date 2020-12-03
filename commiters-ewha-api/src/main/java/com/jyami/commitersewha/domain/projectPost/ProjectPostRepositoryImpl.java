package com.jyami.commitersewha.domain.projectPost;

import com.jyami.commitersewha.domain.post.QPost;
import com.jyami.commitersewha.domain.user.QUser;
import com.jyami.commitersewha.payload.request.SearchRequest;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;


/**
 * Created by jyami on 2020/10/01
 */
@RequiredArgsConstructor
public class ProjectPostRepositoryImpl implements ProjectPostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ProjectPost> findByProjectPostId(long projectPostId) {
        ProjectPost projectPost = jpaQueryFactory.selectFrom(QProjectPost.projectPost)
                .join(QProjectPost.projectPost.user, QUser.user).fetchJoin()
                .leftJoin(QProjectPost.projectPost.likesUser, QUser.user).fetchJoin()
                .where(QProjectPost.projectPost.projectPostId.eq(projectPostId))
                .fetchOne();
        return Optional.ofNullable(projectPost);
    }

    @Override
    public Page<ProjectPost> findAllBySearchCondition(SearchRequest searchRequest) {
        ProjectPostSearchRequestParamMapper queryMapper = new ProjectPostSearchRequestParamMapper(searchRequest);
        JPAQuery<ProjectPost> searchQuery = queryMapper
                .getSearchConditionMapping(jpaQueryFactory.selectFrom(QProjectPost.projectPost));
        QueryResults<ProjectPost> postQueryResults = searchQuery.
                leftJoin(QPost.post.user, QUser.user).fetchJoin()
                .fetchResults();
        return new PageImpl<>(postQueryResults.getResults(), queryMapper.getPageRequest(), postQueryResults.getTotal());
    }

}
