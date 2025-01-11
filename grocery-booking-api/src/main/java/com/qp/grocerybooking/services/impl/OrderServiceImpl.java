package com.qp.grocerybooking.services.impl;

import java.math.BigDecimal;
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

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public Order placeOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.ZERO);

        List<OrderItem> savedItems = new ArrayList<>();

        for (OrderItem item : items) {
            GroceryItem groceryItem = groceryItemRepository.findById(item.getGroceryItem().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Grocery item not found"));

            if (groceryItem.getQuantity().compareTo(item.getQuantity()) < 0 ) {
                throw new IllegalArgumentException("Insufficient stock for item: " + groceryItem.getName());
            }

            groceryItem.setQuantity(groceryItem.getQuantity().subtract(item.getQuantity()));
            groceryItemRepository.save(groceryItem);

            item.setOrder(order);
            item.setPrice(groceryItem.getPrice());
            item.setTotalPrice(groceryItem.getPrice().multiply(new BigDecimal(item.getQuantity())));
            savedItems.add(orderItemRepository.save(item));
        }

        order.setItems(savedItems);
        order.setTotalAmount(savedItems.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        return orderRepository.save(order);
    }

}
