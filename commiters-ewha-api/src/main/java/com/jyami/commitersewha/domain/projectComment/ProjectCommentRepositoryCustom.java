package com.jyami.commitersewha.domain.projectComment;

import java.util.List;

/**
 * Created by jyami on 2020/10/02
 */
public interface ProjectCommentRepositoryCustom {
    List<ProjectComment> findPostByIdWithComments(long postId);
}
