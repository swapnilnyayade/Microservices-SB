package com.swap.ratingservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap.ratingservice.entities.Rating;
import com.swap.ratingservice.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	

	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating createdRating = ratingService.saveRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		List<Rating> allRating = ratingService.getAllRating() ;
		return ResponseEntity.ok(allRating);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> obtainRating(@PathVariable String ratingId){
		Rating rating = ratingService.getRating(ratingId) ;
		return ResponseEntity.ok(rating);
	}
	
	@PutMapping("/{ratingId}")
	public ResponseEntity<Rating> upgradeRating(@PathVariable String ratingId, @RequestBody Rating rating){
		Rating updatedRating = ratingService.updateRating(ratingId, rating);
		return ResponseEntity.ok(updatedRating);
	}

	@DeleteMapping("/{ratingId}")
	public ResponseEntity<String> removeUser(@PathVariable String ratingId){
		ratingService.deleteRating(ratingId);
		return ResponseEntity.ok("Rating successfully deleted!");
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> obtainRatingByUserId(@PathVariable String userId){
		List<Rating> allRating = ratingService.getRatingByUserId(userId) ;
		return ResponseEntity.ok(allRating);
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> obtainRatingByHotelId(@PathVariable String hotelId){
		List<Rating> allRating = ratingService.getRatingByHotelId(hotelId) ;
		return ResponseEntity.ok(allRating);
	}

}
