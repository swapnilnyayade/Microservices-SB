package com.swap.userservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swap.userservice.entities.Hotel;
import com.swap.userservice.entities.Rating;
import com.swap.userservice.entities.User;
import com.swap.userservice.exceptions.ResourceNotFoundException;
import com.swap.userservice.repositories.UserRepository;
import com.swap.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		
		//generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		
		//get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! " + userId));
		
		//fetch rating of the above user from RATING SERVICE
		//http://localhost:8083/ratings/users/8b24520e-72ea-429d-8447-ef68bc56a18c
//		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + userId, ArrayList.class);
//		logger.info("{} ",ratingsOfUser);
		//In the above method it creates problem while using map on ratingsOfUser as response is converted into ArrayList but Rating is not identified(it kept data in linked hashset format)
		 
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
		logger.info("{} ",ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
		//api call to HOTEL SERVICE to get the hotel
		//http://localhost:8082/hotels/fb41df3a-8dac-4a38-9b41-e8d69255a2f6
		ResponseEntity<Hotel> forEntity	= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
		Hotel hotel = forEntity.getBody();
		
		HttpStatusCode statusCode = forEntity.getStatusCode();
		logger.info("response status code {} " + statusCode);
		
		rating.setHotel(hotel);
		
		return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;
	
	}

	@Override
	public User updateUser(String userId, User user) {
		// TODO Auto-generated method stub
		 User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! " + userId));;
	     existingUser.setName(user.getName());
	     existingUser.setEmail(user.getEmail());
	     existingUser.setAbout(user.getAbout());
	     User updatedUser = userRepository.save(existingUser);
	     return updatedUser;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

}

