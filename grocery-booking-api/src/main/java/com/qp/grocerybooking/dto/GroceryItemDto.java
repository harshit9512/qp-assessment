package com.qp.grocerybooking.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GroceryItemDto {
	
	private Long id;
	
	@NotBlank(message = "Item name can not be null or blank")
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigInteger quantity;
	
	@DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
	@NotNull
	private BigDecimal price;
}
