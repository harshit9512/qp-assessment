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
import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.services.UserService;

@RestController
@RequestMapping(ApiEndpoints.USER_BASE_URL)
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping(ApiEndpoints.REGISTER_USER)
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody User user) {
		LOGGER.info(LogMessages.UC_REGISTER_USER_S, user.getName());
		UserResponseDto newUser = userService.registerUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	@PostMapping(ApiEndpoints.LOGIN)
	public ResponseEntity<String> login(@RequestBody User user) {
		String loginResponse = userService.login(user);
		return ResponseEntity.ok(loginResponse);
	}
	
}
