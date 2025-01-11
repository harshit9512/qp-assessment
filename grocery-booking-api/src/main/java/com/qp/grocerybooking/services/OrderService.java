package com.qp.grocerybooking.services;

import java.util.List;

import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.entities.OrderItem;

public interface OrderService {

	Order placeOrder(List<OrderItem> items);

}
