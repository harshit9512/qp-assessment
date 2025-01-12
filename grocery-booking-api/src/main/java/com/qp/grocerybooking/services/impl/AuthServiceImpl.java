package com.qp.grocerybooking.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.exceptions.UnauthorizedAccessException;
import com.qp.grocerybooking.jwt.JwtUtil;
import com.qp.grocerybooking.repositories.UserRepository;
import com.qp.grocerybooking.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public ApiResponseDto<UserResponseDto> registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUserEntity = userRepository.save(user);
		UserResponseDto userDto = modelMapper.map(newUserEntity, UserResponseDto.class);
		return ApiResponseDto.<UserResponseDto>builder().isSuccess(true).message(ResponseMessages.USER_REGISTERED)
				.data(userDto).build();
	}

	@Override
	public ApiResponseDto<UserResponseDto> login(User userRequest) {
		User user = userRepository.findByEmail(userRequest.getEmail());
		if (user == null || !passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
			throw new UnauthorizedAccessException(ResponseMessages.INVALID_CREDENTIALS);
		}
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
		userDto.setToken(token);
		return ApiResponseDto.<UserResponseDto>builder().isSuccess(true).message(ResponseMessages.LOGIN_SUCCESS)
				.data(userDto).build();
	}

}
