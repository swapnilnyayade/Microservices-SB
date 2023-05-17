package com.swap.userservice.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.swap.userservice.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/ratings")
	ResponseEntity<Rating> createRating(@RequestBody Rating rating);
	
	@GetMapping("/ratings")
	ResponseEntity<List<Rating>> getAllRating();
	
	@GetMapping("/ratings/{ratingId}")
	ResponseEntity<Rating> obtainRating(@PathVariable String ratingId);
	
	@PutMapping("/ratings/{ratingId}")
	ResponseEntity<Rating> upgradeRating(@PathVariable String ratingId, @RequestBody Rating rating);

	@DeleteMapping("ratings/{ratingId}")
	ResponseEntity<String> removeUser(@PathVariable String ratingId);
	
	@GetMapping("ratings/users/{userId}")
	ResponseEntity<List<Rating>> obtainRatingByUserId(@PathVariable String userId);

	@GetMapping("ratings/hotels/{hotelId}")
	ResponseEntity<List<Rating>> obtainRatingByHotelId(@PathVariable String hotelId);


	
}
