package com.jyami.commitersewha.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/09/30
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
