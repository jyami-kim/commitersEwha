package com.jyami.commitersewha.security.oauth2.user;

import com.jyami.commitersewha.exception.OAuth2AuthenticationProcessingException;

import java.util.List;
import java.util.Map;

/**
 * Created by jyami on 2020/09/16
 */
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes, List<String> domains) {
        super(attributes);
        domains.stream()
                .filter(domain -> this.getEmail().contains(domain))
                .findAny()
                .orElseThrow(() -> new OAuth2AuthenticationProcessingException("This Email is not EWHA students thing"));
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("given_name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public Boolean getEmailVerified() {
        return (Boolean) attributes.get("email_verified");
    }
}
