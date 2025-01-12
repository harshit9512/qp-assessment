package com.qp.grocerybooking.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qp.grocerybooking.constants.ErrorMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

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
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(", "));
        
        ApiResponseDto<Object> errorResponse = ApiResponseDto.<Object>builder()
                .isSuccess(false)
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
