package com.jyami.commitersewha.security.oauth2.service;

import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.security.oauth2.user.GoogleOAuth2UserInfo;
import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jyami on 2020/10/12
 */
@Service
@RequiredArgsConstructor
public final class GoogleOAuth2UserProcess extends OAuth2UserProcess {

    private final UserRepository userRepository;

    @Override
    OAuth2User getOauth2UserInfoAndProcess(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> oAuth2User) {
        GoogleOAuth2UserInfo googleOAuth2UserInfo = new GoogleOAuth2UserInfo(oAuth2User);

        User user = userRepository.findByEmail(googleOAuth2UserInfo.getEmail())
                .map(value -> updateExistingUser(value, googleOAuth2UserInfo))
                .orElseGet(() -> registerNewUserAsGoogle(googleOAuth2UserInfo));

        return GoogleUserPrincipal.create(user, oAuth2User);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    private User registerNewUserAsGoogle(OAuth2UserInfo oAuth2UserInfo) {
        User user = User.toGoogleInfoEntity(oAuth2UserInfo);
        return userRepository.save(user);
    }
}
