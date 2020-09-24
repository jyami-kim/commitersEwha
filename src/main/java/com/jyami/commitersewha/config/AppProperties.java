package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jyami on 2020/09/14
 */
@Getter
@ConfigurationProperties(prefix = "app")
@Setter
public class AppProperties {
    private final Auth auth = new Auth();
    private String authorizedRedirectUri;

    @Getter
    @NoArgsConstructor
    @Setter
    public static class Auth{
        private String tokenSecret;
        private long tokenExpirationMsec;
    }
}
