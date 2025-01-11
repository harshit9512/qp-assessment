package com.qp.grocerybooking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.grocerybooking.constants.ApiEndpoints;
import com.qp.grocerybooking.constants.LogMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.services.AuthService;

@RestController
@RequestMapping(ApiEndpoints.AUTH_BASE_URL)
public class AuthController {
	
	@Autowired
	AuthService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(ApiEndpoints.REGISTER_USER)
	public ResponseEntity<ApiResponseDto<UserResponseDto>> registerUser(@RequestBody User user) {
		LOGGER.info(LogMessages.UC_REGISTER_USER_S, user.getName());
		ApiResponseDto<UserResponseDto> newUserResponse = userService.registerUser(user);
		return ResponseEntity.ok(newUserResponse);
	}
	
	@PostMapping(ApiEndpoints.LOGIN)
	public ResponseEntity<ApiResponseDto<UserResponseDto>> login(@RequestBody User user) {
		ApiResponseDto<UserResponseDto> loginResponse = userService.login(user);
		return ResponseEntity.ok(loginResponse);
	}
	
}
