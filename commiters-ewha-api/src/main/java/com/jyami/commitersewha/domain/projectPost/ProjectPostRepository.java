package com.jyami.commitersewha.domain.projectPost;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/09/30
 */
public interface ProjectPostRepository extends JpaRepository<ProjectPost, Long>, ProjectPostRepositoryCustom {
}
