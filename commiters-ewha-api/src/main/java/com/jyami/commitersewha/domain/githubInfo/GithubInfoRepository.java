package com.jyami.commitersewha.domain.githubInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jyami on 2020/10/12
 */
public interface GithubInfoRepository extends JpaRepository<GithubInfo, Long> {

    Optional<GithubInfo> findByEmail(String email);
}
