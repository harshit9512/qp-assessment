package com.qp.grocerybooking.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.entities.OrderItem;
import com.qp.grocerybooking.enums.OrderStatus;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.repositories.OrderItemRepository;
import com.qp.grocerybooking.repositories.OrderRepository;
import com.qp.grocerybooking.services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    @Transactional
    public Order placeOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.ZERO);

        for (OrderItem item : items) {
        	OrderItem orderItem = new OrderItem();
            GroceryItem groceryItem = groceryItemRepository.findById(item.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Grocery item not found"));

            if (groceryItem.getQuantity().compareTo(item.getQuantity()) < 0) {
                throw new IllegalArgumentException("Insufficient stock for item: " + groceryItem.getName());
            }

            groceryItem.setQuantity(groceryItem.getQuantity().subtract(item.getQuantity()));
            groceryItemRepository.save(groceryItem);

            orderItem.setOrder(order); // Associate OrderItem with Order
            orderItem.setGroceryItem(groceryItem);
            orderItem.setPrice(groceryItem.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(groceryItem.getPrice().multiply(new BigDecimal(item.getQuantity())));
            order.getItems().add(orderItem); // Add OrderItem to Order
        }

        order.setTotalAmount(order.getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        order.setOrderNumber(this.createOrderNumber());
        return orderRepository.save(order); // Cascade saves Order and OrderItems
    }


    @Transactional
    @Override
	public List<GroceryItem> getAvailableGroceryItems() {
		return groceryItemRepository.findByQuantityNot(BigInteger.ZERO);
	}

	private String createOrderNumber() {
		StringBuilder orderNo = new StringBuilder("PQ");
		orderNo = orderNo.append(LocalDateTime.now().toString());
		return orderNo.toString();
	}
}
