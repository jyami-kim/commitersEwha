package com.jyami.commitersewha.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by jyami on 2020/09/25
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ADMIN", "어드민"),
    USER("USER", "사용자");

    private final String key;
    private final String title;

}

