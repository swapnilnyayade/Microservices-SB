package com.swap.userservice.services;

import java.util.List;

import com.swap.userservice.entities.User;

public interface UserService {

	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	User getUser(String userId);
	
	User updateUser(String userId,User user);
	
	void deleteUser(String userId);
}
