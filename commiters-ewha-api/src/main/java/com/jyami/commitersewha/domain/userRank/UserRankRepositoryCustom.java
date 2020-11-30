package com.jyami.commitersewha.domain.userRank;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by jyami on 2020/11/28
 */
public interface UserRankRepositoryCustom {

    List<UserRank> findAllUserRankinks(boolean week, LocalDate localDate);

}
