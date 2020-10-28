package com.jyami.commitersewha;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jyami on 2020/10/27
 */
@EnableBatchProcessing
@SpringBootApplication
public class CommitersEwhaBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommitersEwhaBatchApplication.class, args);
    }
}
