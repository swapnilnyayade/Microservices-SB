package com.swap.ratingservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swap.ratingservice.entities.Rating;
import com.swap.ratingservice.exceptions.ResourceNotFoundException;
import com.swap.ratingservice.repositories.RatingRepository;
import com.swap.ratingservice.services.RatingService;


@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating saveRating(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public Rating getRating(String ratingId) {
		// TODO Auto-generated method stub
		return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with given id is not found on server!! " + ratingId));
	}

	@Override
	public Rating updateRating(String ratingId, Rating rating) {
		// TODO Auto-generated method stub
		 Rating existingRating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with given id is not found on server!! " + ratingId));;
	     existingRating.setUserId(rating.getUserId());
	     existingRating.setHotelId(rating.getHotelId());
	     existingRating.setRating(rating.getRating());
	     existingRating.setFeedback(rating.getFeedback());
	     Rating updatedRating = ratingRepository.save(existingRating);
	     return updatedRating;
	}

	@Override
	public void deleteRating(String ratingId) {
		// TODO Auto-generated method stub
		ratingRepository.deleteById(ratingId);
	}
	
	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userId);
	}
	
	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelId);
	}
	
	
}
