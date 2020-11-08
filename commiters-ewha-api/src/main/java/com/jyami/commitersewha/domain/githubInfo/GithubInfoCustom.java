package com.jyami.commitersewha.domain.githubInfo;

import java.util.Optional;

/**
 * Created by jyami on 2020/11/08
 */
public interface GithubInfoCustom {

    Optional<GithubInfo> findByUserId(Long userId);
}
