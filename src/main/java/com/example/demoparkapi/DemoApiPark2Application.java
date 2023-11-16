package com.example.demoparkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demoparkapi.entities")
public class DemoApiPark2Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiPark2Application.class, args);
	}

}
