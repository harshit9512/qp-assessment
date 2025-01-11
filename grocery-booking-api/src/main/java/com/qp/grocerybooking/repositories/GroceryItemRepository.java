package com.qp.grocerybooking.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qp.grocerybooking.entities.GroceryItem;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{
	
	List<GroceryItem> findByQuantityNot(BigInteger zero);

}
