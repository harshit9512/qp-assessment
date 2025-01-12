package com.qp.grocerybooking.services;

import com.qp.grocerybooking.dto.UserDto;
import com.qp.grocerybooking.dto.request.LoginRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;

public interface AuthService {

	public ApiResponseDto<UserDto> registerUser(UserDto user);

	public ApiResponseDto<UserDto> login(LoginRequestDto user);

}
