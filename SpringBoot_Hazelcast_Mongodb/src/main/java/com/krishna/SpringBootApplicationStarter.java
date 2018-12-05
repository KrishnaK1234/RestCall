package com.krishna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "com.krishna.*" })
public class SpringBootApplicationStarter {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationStarter.class, args);
	}
}
