package com.qp.grocerybooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qp.grocerybooking.enums.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDto {
	private Long id;
    private String name;
    @NotNull
    @Email(message = "Invalid email format")
    private String email;
    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String token;
}
