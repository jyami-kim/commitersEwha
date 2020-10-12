package com.jyami.commitersewha.security.oauth2.service;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.security.GithubUserPrincipal;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.security.oauth2.user.GithubOAuth2UserInfo;
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
public final class GithubOAuth2UserProcess extends OAuth2UserProcess {

    private final GithubInfoRepository githubInfoRepository;

    @Override
    OAuth2User getOauth2UserInfoAndProcess(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> oAuth2User) {
        GithubOAuth2UserInfo googleOAuth2UserInfo = new GithubOAuth2UserInfo(oAuth2User);

        GithubInfo githubInfo = githubInfoRepository.findByEmail(googleOAuth2UserInfo.getEmail())
                .map(value -> updateExistingUser(value, googleOAuth2UserInfo))
                .orElseGet(() -> registerNewUserAsGithub(googleOAuth2UserInfo));

        return GithubUserPrincipal.create(githubInfo, oAuth2User);
    }

    private GithubInfo updateExistingUser(GithubInfo exsistedInfo, OAuth2UserInfo oAuth2UserInfo) {
        exsistedInfo.setName(oAuth2UserInfo.getName());
        exsistedInfo.setImageUrl(oAuth2UserInfo.getImageUrl());
        return githubInfoRepository.save(exsistedInfo);
    }

    private GithubInfo registerNewUserAsGithub(OAuth2UserInfo newInfo) {
        GithubInfo githubInfo = GithubInfo.toGithubInfoEntity(newInfo);
        return githubInfoRepository.save(githubInfo);
    }
}
