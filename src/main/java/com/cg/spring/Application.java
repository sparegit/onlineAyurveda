package com.cg.spring;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.builders.PathSelectors;

import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Application {

	Logger logger = LogManager.getLogger();

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)

				.select().paths(PathSelectors.any()).build();
	}

}
