package com.jyami.commitersewha.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jyami on 2020/09/14
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
