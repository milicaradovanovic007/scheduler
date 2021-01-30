package com.milicaradovanovic.sap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SapApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapApiApplication.class, args);
	}

}
