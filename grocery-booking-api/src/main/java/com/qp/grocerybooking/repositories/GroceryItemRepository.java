package com.qp.grocerybooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qp.grocerybooking.entities.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{

}
