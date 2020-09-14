package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by jyami on 2020/09/14
 */
@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    @Getter
    @NoArgsConstructor
    public static class Auth{
        private String tockenString;
        private long tokenExpirationMsec;
    }

    @Getter
    @NoArgsConstructor
    private static class OAuth2 {
        private List<String> authorizedRedirectUris;

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
