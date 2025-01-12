package com.qp.grocerybooking.dto.request;

import java.math.BigInteger;
import lombok.Data;

@Data
public class UpdateInventoryRequestDto {

	private BigInteger quantity;
}
