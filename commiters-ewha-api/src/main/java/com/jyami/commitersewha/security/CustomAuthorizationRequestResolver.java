package com.jyami.commitersewha.security;

import com.jyami.commitersewha.exception.NotAccessUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;

/**
 * Created by jyami on 2020/10/12
 */
@Slf4j
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;
    public static final String GOOGLE_TOKEN_HEADER = "token";


    public CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        this.defaultAuthorizationRequestResolver =
                new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository,
                        "/oauth2/authorize/");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest authorizationRequest = this.defaultAuthorizationRequestResolver.resolve(request);
        String googleToken = request.getHeader(GOOGLE_TOKEN_HEADER);
        return authorizationRequest != null ? customAuthorizationRequest(request, authorizationRequest) : null;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest authorizationRequest =
                this.defaultAuthorizationRequestResolver.resolve(request, clientRegistrationId);
        return authorizationRequest != null ? customAuthorizationRequest(request, authorizationRequest) : null;
    }

    private OAuth2AuthorizationRequest customAuthorizationRequest(HttpServletRequest httpServletRequest, OAuth2AuthorizationRequest authorizationRequest) {
        if (authorizationRequest.getAttribute("registration_id").equals("github")) {
            String queryString = httpServletRequest.getQueryString();
            String googleToken = findToken(queryString);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(GOOGLE_TOKEN_HEADER, googleToken);
        }
        return OAuth2AuthorizationRequest.from(authorizationRequest).build();
    }

    private String findToken(String queryString){
        String[] params = queryString.split("&");
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            if(name.equals("token")){
                return value;
            }
        }
        throw new NotAccessUserException("google login이 안되어있습니다.");
    }
}
