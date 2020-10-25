package com.jyami.commitersewha.security;

import com.jyami.commitersewha.exception.NotAccessUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jyami on 2020/10/12
 */
@Slf4j
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;
    public static final String GOOGLE_TOKEN_HEADER = "google-token";


    public CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        this.defaultAuthorizationRequestResolver =
                new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository,
                        "/oauth2/authorize/");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest authorizationRequest = this.defaultAuthorizationRequestResolver.resolve(request);
        String googleToken = request.getHeader(GOOGLE_TOKEN_HEADER);
        return authorizationRequest != null ? customAuthorizationRequest(request, authorizationRequest, googleToken) : null;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest authorizationRequest =
                this.defaultAuthorizationRequestResolver.resolve(request, clientRegistrationId);
        String googleToken = request.getHeader(GOOGLE_TOKEN_HEADER);
        return authorizationRequest != null ? customAuthorizationRequest(request, authorizationRequest, googleToken) : null;
    }

    private OAuth2AuthorizationRequest customAuthorizationRequest(HttpServletRequest httpServletRequest, OAuth2AuthorizationRequest authorizationRequest, String googleToken) {
//        if (authorizationRequest.getAttribute("registration_id").equals("github")) {
//            if (googleToken == null) {
//                throw new NotAccessUserException("google login이 안되어있습니다.");
//            }
//            httpServletRequest.getSession().setAttribute(GOOGLE_TOKEN_HEADER, googleToken);
//        }

        return OAuth2AuthorizationRequest.from(authorizationRequest).build();
    }
}
