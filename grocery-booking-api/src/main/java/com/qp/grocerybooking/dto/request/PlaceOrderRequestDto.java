package com.qp.grocerybooking.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class PlaceOrderRequestDto {
	private List<OrderItemRequestDto> orderItemDetails;
}
