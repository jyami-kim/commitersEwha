package com.jyami.commitersewha.security.oauth2.service;

import com.jyami.commitersewha.exception.OAuth2AuthenticationProcessingException;
import com.jyami.commitersewha.security.oauth2.user.GithubOAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.jyami.commitersewha.security.CustomAuthorizationRequestResolver.GOOGLE_TOKEN_HEADER;

/**
 * Created by jyami on 2020/09/16
 */
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final String GOOGLE = "google";
    private static final String GITHUB = "github";

    private final GoogleOAuth2UserProcess googleOAuth2UserProcess;
    private final GithubOAuth2UserProcess githubOAuth2UserProcess;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equalsIgnoreCase(GOOGLE)) {
            return googleOAuth2UserProcess.processOAuth2User(userRequest, oAuth2User.getAttributes());
        } else if (registrationId.equalsIgnoreCase(GITHUB)) {
            return githubOAuth2UserProcess.processOAuth2User(userRequest, oAuth2User.getAttributes());
        }
        throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
    }


}
