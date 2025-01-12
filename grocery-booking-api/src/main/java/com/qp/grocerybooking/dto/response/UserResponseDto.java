package com.qp.grocerybooking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qp.grocerybooking.enums.UserRole;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserResponseDto {

    private String name;
    private String email;
    private UserRole role;
    private String token;
}
