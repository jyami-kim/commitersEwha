package com.jyami.commitersewha.security.oauth2.user;


import java.util.Map;

/**
 * Created by jyami on 2020/09/16
 */
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
//        validateEmail();
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract Boolean getEmailVerified();
//
//    private void validateEmail() {
//        if (StringUtils.isEmpty(this.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }
//    }
}
