package com.jyami.commitersewha.security.oauth2.service;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import com.jyami.commitersewha.domain.githubInfo.GithubInfoRepository;
import com.jyami.commitersewha.domain.user.User;
import com.jyami.commitersewha.domain.user.UserRepository;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.security.GithubUserPrincipal;
import com.jyami.commitersewha.security.TokenProvider;
import com.jyami.commitersewha.security.oauth2.user.GithubOAuth2UserInfo;
import com.jyami.commitersewha.security.oauth2.user.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.jyami.commitersewha.payload.ResponseMessage.NOT_HAVE_CURRENT_GOOGLE_LOGINED_USER;
import static com.jyami.commitersewha.security.CustomAuthorizationRequestResolver.GOOGLE_TOKEN_HEADER;

/**
 * Created by jyami on 2020/10/12
 */
@Service
@RequiredArgsConstructor
public final class GithubOAuth2UserProcess extends OAuth2UserProcess {

    private final GithubInfoRepository githubInfoRepository;
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    private final TokenProvider tokenProvider;

    @Override
    OAuth2User getOauth2UserInfoAndProcess(OAuth2UserRequest oAuth2UserRequest, Map<String, Object> oAuth2User) {

        GithubOAuth2UserInfo githubOAuth2UserInfo = new GithubOAuth2UserInfo(oAuth2User);
        String tokenValue = oAuth2UserRequest.getAccessToken().getTokenValue();
        GithubInfo githubInfo = githubInfoRepository.findByEmail(githubOAuth2UserInfo.getEmail())
                .map(value -> updateExistingUser(value, tokenValue, githubOAuth2UserInfo))
                .orElseGet(() -> registerNewUserAsGithub(tokenValue, githubOAuth2UserInfo));

        return GithubUserPrincipal.create(githubInfo, oAuth2User);
    }

    private User getCurrentUsersWithGoogleToken() {
        Object token = request.getSession().getAttribute(GOOGLE_TOKEN_HEADER);
        if (token == null) {
            throw new NotAccessUserException(NOT_HAVE_CURRENT_GOOGLE_LOGINED_USER);
        }
        Long userIdFromToken = tokenProvider.getUserIdFromToken((String) token);
        return userRepository.findById(userIdFromToken)
                .orElseThrow(() -> new NotAccessUserException(NOT_HAVE_CURRENT_GOOGLE_LOGINED_USER));
    }

    private GithubInfo updateExistingUser(GithubInfo existedInfo, String token, OAuth2UserInfo oAuth2UserInfo) {
        existedInfo.setName(oAuth2UserInfo.getName());
        existedInfo.setImageUrl(oAuth2UserInfo.getImageUrl());
        existedInfo.setToken(token);
        return githubInfoRepository.save(existedInfo);
    }

    private GithubInfo registerNewUserAsGithub(String token, GithubOAuth2UserInfo newInfo) {
        User currentUsersWithGoogleToken = getCurrentUsersWithGoogleToken();
        GithubInfo githubInfo = toGithubInfoEntity(newInfo, currentUsersWithGoogleToken);
        githubInfo.setToken(token);
        return githubInfoRepository.save(githubInfo);
    }

    private static GithubInfo toGithubInfoEntity(GithubOAuth2UserInfo oAuth2UserInfo, User user) {
        return GithubInfo.builder()
                .email(oAuth2UserInfo.getEmail())
                .authorId(oAuth2UserInfo.getAuthorId())
                .providerId(Long.parseLong(oAuth2UserInfo.getId()))
                .name(oAuth2UserInfo.getName())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .user(user)
                .build();
    }

}
