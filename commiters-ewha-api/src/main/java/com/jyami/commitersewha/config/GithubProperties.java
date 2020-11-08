package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Created by jyami on 2020/11/01
 */

@Getter
@NoArgsConstructor
@Setter
@ConfigurationProperties(prefix = "github")
public class GithubProperties {
    private Duration connectionTimeout;
    private Duration readTimeout;
    private String host;
}
