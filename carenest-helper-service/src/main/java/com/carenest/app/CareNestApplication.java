package com.carenest.app;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableRabbit
@ComponentScan(basePackages = "com.carenest")
@EnableJpaRepositories(basePackages = {"com.carenest.helper.repository",  "com.carenest.patient.repository"})
@EntityScan(basePackages = {"com.carenest.helper.entity", "com.carenest.patient.entity"})
public class CareNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareNestApplication.class, args);
	}
}
