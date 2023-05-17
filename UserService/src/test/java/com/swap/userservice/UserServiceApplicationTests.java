package com.swap.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.swap.userservice.entities.Rating;
import com.swap.userservice.externalservices.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(10).feedback("createRating Working").build();
		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
		System.out.println("new rating created");
	}

}
