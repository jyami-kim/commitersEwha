package com.jyami.commitersewha;

import com.jyami.commitersewha.config.GithubProperties;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by jyami on 2020/10/27
 */
@EnableBatchProcessing
@SpringBootApplication
@EnableConfigurationProperties(value = {GithubProperties.class})
public class CommitersEwhaBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommitersEwhaBatchApplication.class, args);
    }
}
