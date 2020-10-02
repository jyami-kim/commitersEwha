package com.jyami.commitersewha.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/10/01
 */
public interface PostRepositoryCustom {

    Optional<Post> findPostByIdWithComments(long postId);

    List<Comment> findPostByIdWithComments2(long postId);
}
