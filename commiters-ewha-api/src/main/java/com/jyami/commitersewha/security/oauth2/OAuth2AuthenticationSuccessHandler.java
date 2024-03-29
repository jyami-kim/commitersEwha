package com.jyami.commitersewha.security.oauth2;

import com.jyami.commitersewha.config.AppProperties;
import com.jyami.commitersewha.exception.BadRequestException;
import com.jyami.commitersewha.exception.NotAccessUserException;
import com.jyami.commitersewha.security.GithubUserPrincipal;
import com.jyami.commitersewha.security.GoogleUserPrincipal;
import com.jyami.commitersewha.security.TokenProvider;
import com.jyami.commitersewha.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/21
 */
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already bean committed. Unable to redirect to " + targetUrl);
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        Object principal = authentication.getPrincipal();
        if (principal instanceof GoogleUserPrincipal) {
            String token = tokenProvider.createToken((GoogleUserPrincipal) authentication.getPrincipal());
            return UriComponentsBuilder.fromUriString(targetUrl)
                    .queryParam("token", token)
                    .build().toUriString();
        }
        if (principal instanceof GithubUserPrincipal) {
            String token = tokenProvider.createToken((GithubUserPrincipal) authentication.getPrincipal());
            return UriComponentsBuilder.fromUriString(targetUrl)
                    .queryParam("github-token", token)
                    .build().toUriString();
        }
        throw new NotAccessUserException("올바르지 않은 유저 접근입니다.");
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUrl = URI.create(uri);
        URI authorizedURI = URI.create(appProperties.getAuthorizedRedirectUri());

        return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUrl.getHost())
                && authorizedURI.getPort() == clientRedirectUrl.getPort();
    }


}
