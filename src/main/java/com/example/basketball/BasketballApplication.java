package com.example.basketball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BasketballApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketballApplication.class, args);
	}

	//exclude = SecurityAutoConfiguration.class

}
