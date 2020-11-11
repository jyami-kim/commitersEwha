package com.jyami.commitersewha.exception;

import lombok.Getter;

/**
 * Created by jyami on 2020/11/01
 */
@Getter
public class RestTemplateResponseException extends RuntimeException {

    private int status;

    public RestTemplateResponseException() {
    }

    public RestTemplateResponseException(String message) {
        super(message);
    }

    public RestTemplateResponseException(String message, int status) {
        super(message);
        this.status = status;
    }
}
