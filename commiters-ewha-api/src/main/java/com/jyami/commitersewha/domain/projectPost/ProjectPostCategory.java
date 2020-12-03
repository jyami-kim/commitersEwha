package com.jyami.commitersewha.domain.projectPost;

import lombok.Getter;

/**
 * Created by jyami on 2020/10/02
 */
@Getter
public enum ProjectPostCategory {
    ADVERTISING(1, "홍보"),
    JOB(2, "구인");

    ProjectPostCategory(Integer ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
    }

    private Integer ordinal;
    private String name;
}
