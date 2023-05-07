package com.swap.ratingservice.services;

import java.util.List;

import com.swap.ratingservice.entities.Rating;

public interface RatingService {

	// rating operations

	// create
	Rating saveRating(Rating rating);

	// get all rating
	List<Rating> getAllRating();

	// get single rating of given ratingId
	Rating getRating(String ratingId);

	Rating updateRating(String ratingId, Rating Rating);

	void deleteRating(String ratingId);

	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);

}
