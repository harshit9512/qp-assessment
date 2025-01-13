package com.qp.grocerybooking.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ErrorMessages;
import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.request.OrderItemRequestDto;
import com.qp.grocerybooking.dto.request.PlaceOrderRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.entities.OrderItem;
import com.qp.grocerybooking.enums.OrderStatus;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.repositories.OrderRepository;
import com.qp.grocerybooking.services.OrderService;
import com.qp.grocerybooking.utilities.CommonUtils;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Override
	@Transactional
	public ApiResponseDto<Order> placeOrder(PlaceOrderRequestDto placeOrderRequest) {
		Order order = new Order();
		order.setStatus(OrderStatus.PENDING);
		order.setTotalAmount(BigDecimal.ZERO);

		for (OrderItemRequestDto item : placeOrderRequest.getOrderItemDetails()) {
			OrderItem orderItem = new OrderItem();
			GroceryItem groceryItem = groceryItemRepository.findById(item.getId())
					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ITEM_NOT_FOUND));

			if (groceryItem.getQuantity().compareTo(item.getQuantity()) < 0) {
				return ApiResponseDto.<Order>builder().isSuccess(false).message(ResponseMessages.INSUFFICIENT_STOCK_FOR_ITEM + groceryItem.getName()).data(null)
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
		return ApiResponseDto.<Order>builder().isSuccess(true).message(ResponseMessages.ORDER_PLACED).data(savedOrder)
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
		String randomNumber = CommonUtils.generateRandomNumberString(4);
		return "PQ" + date + randomNumber;
	}
}
