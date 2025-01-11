package com.qp.grocerybooking.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.exceptions.UnauthorizedAccessException;
import com.qp.grocerybooking.repositories.UserRepository;
import com.qp.grocerybooking.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ApiResponseDto<UserResponseDto> registerUser(User user) {
		User newUserEntity = userRepository.save(user);
		UserResponseDto userDto = modelMapper.map(newUserEntity, UserResponseDto.class);
		return ApiResponseDto.<UserResponseDto>builder().isSuccess(true).message(ResponseMessages.USER_REGISTERED).data(userDto).build();
	}

	@Override
	public ApiResponseDto<UserResponseDto> login(User userRequest) {
		User user = userRepository.findByEmail(userRequest.getEmail());
		UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
		if(userRequest.getPassword().equalsIgnoreCase(user.getPassword())) {
			return ApiResponseDto.<UserResponseDto>builder().isSuccess(true).message(ResponseMessages.LOGIN_SUCCESS).data(userDto).build();
		} else {
			throw new UnauthorizedAccessException(ResponseMessages.INCORRECT_PASSWORD);
		}
	}

}
