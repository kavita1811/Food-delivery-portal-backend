package com.zomato.zomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApplication.class, args);
		System.out.println("service started successfully");
	}


}
