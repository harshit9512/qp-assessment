package com.qp.grocerybooking.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ErrorMessages;
import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.UserDto;
import com.qp.grocerybooking.dto.request.LoginRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.exceptions.ApiException;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.exceptions.UnauthorizedAccessException;
import com.qp.grocerybooking.jwt.JwtUtil;
import com.qp.grocerybooking.repositories.UserRepository;
import com.qp.grocerybooking.services.AuthService;

import jakarta.transaction.Transactional;

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
	@Transactional
	public ApiResponseDto<UserDto> registerUser(UserDto userDto) {
		Optional<User> userOpt = userRepository.findByEmail(userDto.getEmail());
		if (userOpt.isPresent()) {
			return ApiResponseDto.<UserDto>builder().isSuccess(false).message(ResponseMessages.USER_ALREADY_EXIST)
					.data(userDto).build();
		}
		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User newUserEntity = userRepository.save(user);
		userDto.setId(newUserEntity.getId());
		userDto.setPassword(null);
		return ApiResponseDto.<UserDto>builder().isSuccess(true).message(ResponseMessages.USER_REGISTERED).data(userDto)
				.build();
	}

	@Override
	public ApiResponseDto<UserDto> login(LoginRequestDto loginRequest) {
		Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
		if (userOpt.isEmpty()) {
			throw new ResourceNotFoundException(ErrorMessages.USER_NOT_FOUND);
		}
		User user = userOpt.get();
		if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new UnauthorizedAccessException(ResponseMessages.INVALID_CREDENTIALS);
		}
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setPassword(null);
		userDto.setToken(token);
		return ApiResponseDto.<UserDto>builder().isSuccess(true).message(ResponseMessages.LOGIN_SUCCESS).data(userDto)
				.build();
	}

}
