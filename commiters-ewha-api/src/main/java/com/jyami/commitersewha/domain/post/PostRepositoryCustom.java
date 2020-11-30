package com.jyami.commitersewha.domain.post;

import com.jyami.commitersewha.domain.comment.Comment;
import com.jyami.commitersewha.payload.request.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/10/01
 */
public interface PostRepositoryCustom {

    Optional<Post> findByPostId(long postId);

    Page<Post> findAllBySearchCondition(SearchRequest searchRequest);
}
