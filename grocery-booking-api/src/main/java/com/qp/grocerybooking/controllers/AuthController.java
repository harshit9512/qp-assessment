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
import com.qp.grocerybooking.dto.UserDto;
import com.qp.grocerybooking.dto.request.LoginRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.AUTH_BASE_URL)
public class AuthController {
	
	@Autowired
	AuthService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(ApiEndpoints.REGISTER_USER)
	public ResponseEntity<ApiResponseDto<UserDto>> registerUser(@Valid @RequestBody UserDto user) {
		LOGGER.info(LogMessages.UC_REGISTER_USER_S, user.getName());
		ApiResponseDto<UserDto> newUserResponse = userService.registerUser(user);
		return ResponseEntity.ok(newUserResponse);
	}
	
	@PostMapping(ApiEndpoints.LOGIN)
	public ResponseEntity<ApiResponseDto<UserDto>> login(@Valid @RequestBody LoginRequestDto user) {
		ApiResponseDto<UserDto> loginResponse = userService.login(user);
		return ResponseEntity.ok(loginResponse);
	}
}
