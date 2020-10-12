package com.jyami.commitersewha.security.oauth2.service;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * Created by jyami on 2020/10/12
 */
public abstract class OAuth2UserProcess {

    public OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> oAuth2User) {
        try {
            return getOauth2UserInfoAndProcess(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    abstract OAuth2User getOauth2UserInfoAndProcess(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> oAuth2User);

}
