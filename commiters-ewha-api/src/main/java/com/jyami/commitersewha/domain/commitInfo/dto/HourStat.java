package com.jyami.commitersewha.domain.commitInfo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/11/24
 */
@Getter
@ToString
public class HourStat {

    private Long count;
    private Integer hour;

    @QueryProjection
    public HourStat(Long count, Integer hour) {
        this.count = count;
        this.hour = hour;
    }
}
