package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.payload.request.SearchRequest;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by jyami on 2020/10/02
 */
@RequiredArgsConstructor
public final class PostSearchRequestParamMapper {

    private final SearchRequest searchRequest;

    public <T> JPAQuery<T> getSearchConditionMapping(JPAQuery<T> query) {
        JPAQuery<T> mapping = getCategoryMapping(query);
        mapping = titleSearchMapping(mapping);
        return getHashTagMapping(mapping);
    }

    public PageRequest getPageRequest(){
        return searchRequest.toPageRequest();
    }

    private <T> JPAQuery<T> getCategoryMapping(JPAQuery<T> query) {
        if (searchRequest.getCategory() == null) {
            return query;
        }
        return query.where(QPost.post.category.eq(searchRequest.getCategory()));
    }

    private <T> JPAQuery<T> getHashTagMapping(JPAQuery<T> query) {
        List<String> hashTag = searchRequest.getHashTag();
        if (hashTag == null || hashTag.isEmpty()) {
            return query;
        }
        BooleanExpression likeQuery = getHashTagSearchExpression(hashTag);
        return query.where(likeQuery);
    }

    private <T> JPAQuery<T> titleSearchMapping(JPAQuery<T> query) {
        String title = searchRequest.getTitle();
        if (StringUtils.isBlank(title)) {
            return query;
        }
        return query.where(QPost.post.title.containsIgnoreCase(title));
    }

    private BooleanExpression getHashTagSearchExpression(List<String> hashTag) {
        BooleanExpression hashExpression = QPost.post.hashTags.containsIgnoreCase(hashTag.get(0));
        for (int i = 1; i < hashTag.size(); i++) {
            hashExpression = hashExpression.or(QPost.post.hashTags.containsIgnoreCase(hashTag.get(i)));
        }
        return hashExpression;
    }


}
