package com.jyami.commitersewha.domain.user;

import java.util.Optional;

/**
 * Created by jyami on 2020/10/03
 */
public interface UserRepositoryCustom {

    Optional<User> findBySubId(String subId);

    Optional<User> findByUserId(Long userId);
}
