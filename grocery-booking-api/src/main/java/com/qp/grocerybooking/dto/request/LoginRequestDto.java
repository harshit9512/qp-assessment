package com.qp.grocerybooking.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {
	@NotNull
	@Email(message = "Invalid email format")
	private String email;
	@NotNull
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
}
