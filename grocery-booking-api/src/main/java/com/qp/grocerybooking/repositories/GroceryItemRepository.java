package com.qp.grocerybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.grocerybooking.entities.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{

}
