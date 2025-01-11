package com.qp.grocerybooking.services;

import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;

public interface UserService {

	public UserResponseDto registerUser(User user);

	public String login(User user);

}
