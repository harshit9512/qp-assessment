package com.qp.grocerybooking.dto.request;

import java.math.BigInteger;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDto {
	@NotNull
	@Min(1)
	private Long id;
	
	@Min(1)
	private BigInteger quantity;
}
