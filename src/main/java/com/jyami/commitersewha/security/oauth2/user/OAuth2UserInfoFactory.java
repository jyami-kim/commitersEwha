package com.jyami.commitersewha.security.oauth2.user;

import com.jyami.commitersewha.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

/**
 * Created by jyami on 2020/09/16
 */
public class OAuth2UserInfoFactory {

    private static String GOOGLE = "google";

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(GOOGLE)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
