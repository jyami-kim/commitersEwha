package com.jyami.commitersewha.domain.commitInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/11/09
 */
public interface CommitInfoRepository extends JpaRepository<GithubCommitInfo, Long>, CommitInfoCustom {

}
