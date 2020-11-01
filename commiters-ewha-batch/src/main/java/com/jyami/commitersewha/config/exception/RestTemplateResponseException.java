package com.jyami.commitersewha.config.exception;

/**
 * Created by jyami on 2020/11/01
 */
public class RestTemplateResponseException extends RuntimeException {
    public RestTemplateResponseException() {
    }

    public RestTemplateResponseException(String message) {
        super(message);
    }
}
