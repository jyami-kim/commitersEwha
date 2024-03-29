package com.jyami.commitersewha;

import com.jyami.commitersewha.config.AppProperties;
import com.jyami.commitersewha.config.GithubProperties;
import com.jyami.commitersewha.config.ScrapingProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value = {AppProperties.class, ScrapingProperties.class, GithubProperties.class})
@EnableJpaAuditing
@SpringBootApplication
public class CommitersEwhaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommitersEwhaApiApplication.class, args);
    }

}
