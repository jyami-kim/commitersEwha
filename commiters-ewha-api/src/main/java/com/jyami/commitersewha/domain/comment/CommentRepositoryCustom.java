package com.jyami.commitersewha.domain.comment;

import java.util.List;

/**
 * Created by jyami on 2020/10/02
 */
public interface CommentRepositoryCustom {
    List<Comment> findPostByIdWithComments(long postId);
}
