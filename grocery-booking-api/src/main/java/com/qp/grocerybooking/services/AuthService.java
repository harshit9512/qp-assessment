package com.qp.grocerybooking.services;

import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;

public interface AuthService {

	public UserResponseDto registerUser(User user);

	public String login(User user);

}
