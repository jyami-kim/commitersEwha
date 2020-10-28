package com.jyami.commitersewha.exception;

/**
 * Created by jyami on 2020/09/16
 */
public class OAuth2AuthenticationProcessingException extends RuntimeException{
    public OAuth2AuthenticationProcessingException() {
    }

    public OAuth2AuthenticationProcessingException(String message) {
        super(message);
    }
}
