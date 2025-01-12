package com.qp.grocerybooking.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class UpdateGroceryItemRequestDto {
	private String name;
	private String description;
	@DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
	private BigDecimal price;
}
