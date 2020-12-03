package com.jyami.commitersewha.domain.projectPost;

import com.jyami.commitersewha.payload.request.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Created by jyami on 2020/10/01
 */
public interface ProjectPostRepositoryCustom {

    Optional<ProjectPost> findByProjectPostId(long projectPostId);

    Page<ProjectPost> findAllBySearchCondition(SearchRequest searchRequest);
}
