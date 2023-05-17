package com.swap.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.swap.userservice.entities.Rating;
import com.swap.userservice.externalservices.RatingService;

@SpringBootApplication
//@EnableEurekaClient is not imported/supported(no need to add)
@EnableFeignClients
public class UserServiceApplication {
	
	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	

}

