package com.qp.grocerybooking.entities;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
	private Order order;
	
	@JoinColumn(name = "grocery_item_id", nullable = false)
	@ManyToOne
	private GroceryItem groceryItem;
	
	@Column(nullable = false)
	private BigInteger quantity;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false)
	private LocalDateTime updatedOn;
	
}
