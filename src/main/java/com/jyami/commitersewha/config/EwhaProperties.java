package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jyami on 2020/10/03
 */

@Getter
@ConfigurationProperties(prefix = "ewha")
@Setter
public class EwhaProperties {
    private String scrapJobUrl;
    private String scrapNotification;
}
