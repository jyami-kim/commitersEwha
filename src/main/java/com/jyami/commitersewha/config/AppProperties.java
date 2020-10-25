package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by jyami on 2020/09/14
 */
@Getter
@ConfigurationProperties(prefix = "app")
@Setter
public class AppProperties {
    private final Auth auth = new Auth();
    private String authorizedRedirectUri;
    private List<String> domain;

    @Getter
    @NoArgsConstructor
    @Setter
    public static class Auth{
        private String tokenSecret;
        private long tokenExpirationMsec;
    }
}
