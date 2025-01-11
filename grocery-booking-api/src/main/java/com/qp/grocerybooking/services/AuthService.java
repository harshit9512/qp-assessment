package com.qp.grocerybooking.services;

import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.dto.response.UserResponseDto;
import com.qp.grocerybooking.entities.User;

public interface AuthService {

	public ApiResponseDto<UserResponseDto> registerUser(User user);

	public ApiResponseDto<UserResponseDto> login(User user);

}
