package com.jyami.commitersewha.domain.commitInfo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/11/22
 */
@Getter
@ToString
public class CommitMap {

    private Long commitCount;
    private String commitDate;

    @QueryProjection
    public CommitMap( Long commitCount, String yyyymmdd) {
        this.commitCount = commitCount;
        this.commitDate = yyyymmdd;
    }

}
