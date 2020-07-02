package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cg")
@SpringBootApplication
@EntityScan(basePackages="com.cg.model")
public class SpringBankBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBankBootApplication.class, args);
	}

}
