package com.jyami.commitersewha.domain.userRank;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/11/28
 */
public interface UserRankRepository extends JpaRepository<UserRank, Long>, UserRankRepositoryCustom {

    Optional<UserRank> findByGithubInfoAndWeekAndLocalDate(GithubInfo githubInfo, boolean week, LocalDate localDate);

}
