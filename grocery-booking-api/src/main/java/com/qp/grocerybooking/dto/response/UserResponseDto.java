package com.qp.grocerybooking.dto.response;

import com.qp.grocerybooking.enums.UserRole;

import lombok.Data;

@Data
public class UserResponseDto {

    private String name;
    private String email;
    private UserRole role;
}
