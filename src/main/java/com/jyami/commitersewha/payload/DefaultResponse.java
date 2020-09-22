package com.jyami.commitersewha.payload;

import lombok.Getter;

/**
 * Created by jyami on 2020/09/22
 */
@Getter
public class DefaultResponse<T> {
    private int status;
    private String message;
    private T body;
}
