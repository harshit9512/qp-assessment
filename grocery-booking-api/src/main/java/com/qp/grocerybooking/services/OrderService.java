package com.qp.grocerybooking.services;

import java.util.List;

import com.qp.grocerybooking.dto.request.PlaceOrderRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;

public interface OrderService {


	ApiResponseDto<Order> placeOrder(PlaceOrderRequestDto items);

	ApiResponseDto<List<GroceryItem>> getAvailableGroceryItems();

}
