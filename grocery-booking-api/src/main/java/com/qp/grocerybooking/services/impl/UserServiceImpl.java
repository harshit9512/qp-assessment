package com.qp.grocerybooking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.repositories.UserRepository;
import com.qp.grocerybooking.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

}
