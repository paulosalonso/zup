package com.github.paulosalonso.zup.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.paulosalonso.zup")
@EntityScan(basePackages = "com.github.paulosalonso.zup")
@EnableJpaRepositories(basePackages = "com.github.paulosalonso.zup")
public class ZupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZupApplication.class, args);
	}

}
