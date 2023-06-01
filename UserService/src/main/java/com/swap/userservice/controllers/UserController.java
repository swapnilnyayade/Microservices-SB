package com.swap.userservice.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap.userservice.entities.User;
import com.swap.userservice.impl.UserServiceImpl;
import com.swap.userservice.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private int retryCount = 1;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser() ;
		return ResponseEntity.ok(allUser);
	}
	
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> obtainUser(@PathVariable String userId){
		logger.info("Retry Count: {}", retryCount);
		retryCount++;
		User user = userService.getUser(userId) ;
		return ResponseEntity.ok(user);
	}
	
	//creating fallback method for circuitbreaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("Fallback is executed because service is down : ", ex.getMessage());
		User user = User.builder().
				email("dumy@gmail.com").
				name("Dummy").
				about("This user is created dummy because some service is down").
				userId("12345").
				build();
		return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> upgradeUser(@PathVariable String userId, @RequestBody User user){
		User updatedUser = userService.updateUser(userId, user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable String userId){
		userService.deleteUser(userId);
		return ResponseEntity.ok("User successfully deleted!");
	}

	@PostMapping("/user")
	public ResponseEntity<String> postUser(@RequestBody Map<String, String> values){
		return ResponseEntity.ok("User successfully posted "+ values.get("id"));
	}

	
}
