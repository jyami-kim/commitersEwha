package com.jyami.commitersewha.domain.projectComment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/09/30
 */
public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long>, ProjectCommentRepositoryCustom {
}
