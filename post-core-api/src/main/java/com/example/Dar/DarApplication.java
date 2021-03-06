package com.example.Dar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarApplication.class, args);
	}

}
