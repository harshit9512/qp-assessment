package com.qp.grocerybooking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.grocerybooking.constants.ApiEndpoints;
import com.qp.grocerybooking.entities.User;
import com.qp.grocerybooking.services.UserService;

@RestController
@RequestMapping(ApiEndpoints.USER_BASE_URL)
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping(ApiEndpoints.REGISTER_USER)
	public User registerUser(@RequestBody User user) {
		User newUser = userService.registerUser(user);
		return newUser;
	}
}
