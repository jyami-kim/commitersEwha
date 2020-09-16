package com.jyami.commitersewha.domain;

import com.jyami.commitersewha.exception.OAuth2AuthenticationProcessingException;

import java.util.Arrays;

/**
 * Created by jyami on 2020/09/14
 */
public enum AuthProvider {
    google,
    github;

    public static AuthProvider getEqualsToIgnoreCase(String provider) {
        return Arrays.stream(AuthProvider.values())
                .filter(p -> p.toString().equalsIgnoreCase(provider))
                .findAny()
                .orElseThrow(() -> new OAuth2AuthenticationProcessingException("Sorry! It is invalid login, that is not supported you"));
    }
}
