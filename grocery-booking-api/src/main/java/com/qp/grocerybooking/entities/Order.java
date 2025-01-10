package com.qp.grocerybooking.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.qp.grocerybooking.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_number", nullable = false)
	private String orderNumber;
	
	@Column(name = "total_amount", nullable = false)
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();
	
	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false)
	private LocalDateTime updatedOn;
}
