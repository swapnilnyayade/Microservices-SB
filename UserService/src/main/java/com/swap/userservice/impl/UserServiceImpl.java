package com.swap.userservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swap.userservice.entities.User;
import com.swap.userservice.exceptions.ResourceNotFoundException;
import com.swap.userservice.repositories.UserRepository;
import com.swap.userservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

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
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server!! " + userId));
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

