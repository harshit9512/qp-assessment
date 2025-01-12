package com.qp.grocerybooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.grocerybooking.constants.ApiEndpoints;
import com.qp.grocerybooking.constants.AppContants;
import com.qp.grocerybooking.dto.request.PlaceOrderRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.entities.Order;
import com.qp.grocerybooking.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.ORDER_BASE_URL)
public class OrderController {
	
    @Autowired
    private OrderService orderService;

    @Secured(AppContants.ROLE_USER)
    @PostMapping
    public ResponseEntity<ApiResponseDto<Order>> placeOrder(@Valid @RequestBody PlaceOrderRequestDto placeOrderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(placeOrderRequest));
    }
    
    @Secured(AppContants.ROLE_USER)
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<GroceryItem>>> getAvailableGroceryItems() {
    	return ResponseEntity.ok(orderService.getAvailableGroceryItems());
    }
}
