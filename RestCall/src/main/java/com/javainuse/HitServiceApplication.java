package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.javainuse.*")
public class HitServiceApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(HitServiceApplication.class, args);
	}
}
