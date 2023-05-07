package com.swap.ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient is not imported/supported(no need to add)
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

}
