package com.qp.grocerybooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qp.grocerybooking.constants.ErrorMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;

@ControllerAdvice
public class ApplicationGlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseDto<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ApiResponseDto<Object> errorResponse = ApiResponseDto.<Object>builder().isSuccess(false)
				.message(ErrorMessages.DEFAULT_ERR_MSG).errorMessage(ex.getMessage()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponseDto<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
		ApiResponseDto<Object> errorResponse = ApiResponseDto.<Object>builder().isSuccess(false)
				.message(ErrorMessages.DEFAULT_ERR_MSG).errorMessage(ex.getMessage()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseDto<Object>> handleGlobalException(Exception ex) {
		ApiResponseDto<Object> errorResponse = ApiResponseDto.<Object>builder().isSuccess(false)
				.message(ErrorMessages.DEFAULT_ERR_MSG).errorMessage(ex.getMessage()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiResponseDto<Object>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
		ApiResponseDto<Object> errorResponse = ApiResponseDto.<Object>builder().isSuccess(false)
				.message(ErrorMessages.DEFAULT_ERR_MSG).errorMessage(ex.getMessage()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}
}
