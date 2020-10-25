package com.jyami.commitersewha.controller;

import com.jyami.commitersewha.exception.OAuth2AuthenticationProcessingException;
import com.jyami.commitersewha.payload.DefaultResponse;
import com.jyami.commitersewha.payload.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by jyami on 2020/10/25
 */
@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler({OAuth2AuthenticationProcessingException.class, InternalAuthenticationServiceException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public DefaultResponse<?> unAuthentication(Exception e) {
        return DefaultResponse.of(ResponseCode.DOMAIN_ERROR, e.getMessage());
    }
}
