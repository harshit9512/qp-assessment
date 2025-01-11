package com.qp.grocerybooking.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.repositories.UserRepository;
import com.qp.grocerybooking.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserResponseDto registerUser(User user) {
		User newUserEntity = userRepository.save(user);
		return modelMapper.map(newUserEntity, UserResponseDto.class);
	}

	@Override
	public String login(User userRequest) {
		User user = userRepository.findByEmail(userRequest.getEmail());
		if(userRequest.getPassword().equalsIgnoreCase(user.getPassword())) {
			return "login successful";
		} else {
			return "Login failed, password incorrect";
		}
	}

}
