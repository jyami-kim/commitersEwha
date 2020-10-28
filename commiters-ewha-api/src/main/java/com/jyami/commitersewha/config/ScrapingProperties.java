package com.jyami.commitersewha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jyami on 2020/10/03
 */

@Getter
@ConfigurationProperties(prefix = "scraping")
@Setter
public class ScrapingProperties {

    private Baekjoon baekjoon = new Baekjoon();
    private Ewha ewha = new Ewha();

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Baekjoon {
        private String baseUrl;
        private String scrapUrl;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Ewha {
        private String baseUrl;
        private String scrapJobUrl;
        private String scrapNotification;
    }
}
