package com.jyami.commitersewha;

import com.jyami.commitersewha.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties(value = {AppProperties.class})
@EnableJpaAuditing
@SpringBootApplication
public class CommitersEwhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitersEwhaApplication.class, args);
	}

}
