package com.qp.grocerybooking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Builder
public class ApiResponseDto<T> {
	private Boolean isSuccess;
	private String message;
	private T data;
	private String errorMessage;
}
