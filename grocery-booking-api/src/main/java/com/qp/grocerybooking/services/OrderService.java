package com.qp.grocerybooking.services;

import java.util.List;

import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.entities.OrderItem;

public interface OrderService {


	ApiResponseDto<Order> placeOrder(List<OrderItem> items);

	ApiResponseDto<List<GroceryItem>> getAvailableGroceryItems();

}
