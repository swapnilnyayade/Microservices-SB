package com.swap.userservice.controllers;

import java.util.List;
import java.util.Map;

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

import com.swap.userservice.entities.User;
import com.swap.userservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
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
	public ResponseEntity<User> obtainUser(@PathVariable String userId){
		User user = userService.getUser(userId) ;
		return ResponseEntity.ok(user);
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
	public ResponseEntity<String> postUser(Map<String, String> values){
		return ResponseEntity.ok("User successfully posted"+ values.get("id"));
	}

	
}
