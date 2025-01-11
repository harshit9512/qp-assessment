package com.qp.grocerybooking.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.entities.OrderItem;
import com.qp.grocerybooking.enums.OrderStatus;
import com.qp.grocerybooking.exceptions.ApiException;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.repositories.OrderRepository;
import com.qp.grocerybooking.services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Override
	@Transactional
	public ApiResponseDto<Order> placeOrder(List<OrderItem> items) {
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING);
		order.setTotalAmount(BigDecimal.ZERO);

		for (OrderItem item : items) {
			OrderItem orderItem = new OrderItem();
			GroceryItem groceryItem = groceryItemRepository.findById(item.getId())
					.orElseThrow(() -> new ResourceNotFoundException("Grocery item not found."));

			if (groceryItem.getQuantity().compareTo(item.getQuantity()) < 0) {
				return ApiResponseDto.<Order>builder().isSuccess(false).message("Insufficient stock for item: " + groceryItem.getName()).data(null)
						.build();
			}

			groceryItem.setQuantity(groceryItem.getQuantity().subtract(item.getQuantity()));
			groceryItemRepository.save(groceryItem);

			orderItem.setOrder(order);
			orderItem.setGroceryItem(groceryItem);
			orderItem.setPrice(groceryItem.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotalPrice(groceryItem.getPrice().multiply(new BigDecimal(item.getQuantity())));
			order.getItems().add(orderItem);
		}

		order.setTotalAmount(
				order.getItems().stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

		order.setOrderNumber(this.generateOrderNumber());
		Order savedOrder = orderRepository.save(order);
		return ApiResponseDto.<Order>builder().isSuccess(true).message("Order placed successfully").data(savedOrder)
				.build();
	}

	@Transactional
	@Override
	public ApiResponseDto<List<GroceryItem>> getAvailableGroceryItems() {
		List<GroceryItem> groceryItems = groceryItemRepository.findByQuantityNot(BigInteger.ZERO);
		return ApiResponseDto.<List<GroceryItem>>builder().isSuccess(true).data(groceryItems)
				.message("Data fetched successfully").build();
	}

	private String generateOrderNumber() {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

		Random random = new Random();
		int randomNumber = 1000 + random.nextInt(9000);

		return "PQ" + date + randomNumber;
	}
}
