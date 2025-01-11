package com.qp.grocerybooking.services;

import java.math.BigInteger;
import java.util.List;

import com.qp.grocerybooking.entities.GroceryItem;

public interface GroceryItemService {

	GroceryItem addGroceryItem(GroceryItem item);

	List<GroceryItem> getAllGroceryItems();

	void deleteGroceryItem(Long id);

	GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem);

	GroceryItem updateInventory(Long id, BigInteger quantity);

}
