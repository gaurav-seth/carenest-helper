package com.carenest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.carenest")
@EnableJpaRepositories(basePackages = "com.carenest.helper.repository")
@EntityScan(basePackages = "com.carenest.helper.entity")
public class CareNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareNestApplication.class, args);
	}
}
