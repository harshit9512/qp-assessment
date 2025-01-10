package com.qp.grocerybooking.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "grocery_items")
public class GroceryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private BigInteger quantity;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	
	@Column(name = "updated_on", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	
}
