package com.jyami.commitersewha;

import com.jyami.commitersewha.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {AppProperties.class})
@SpringBootApplication
public class CommitersEwhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitersEwhaApplication.class, args);
	}

}
