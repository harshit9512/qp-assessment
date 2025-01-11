package com.qp.grocerybooking.services;

import java.math.BigInteger;
import java.util.List;

import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;

public interface GroceryItemService {

	ApiResponseDto<GroceryItem> addGroceryItem(GroceryItem item);

	ApiResponseDto<List<GroceryItem>> getAllGroceryItems();

	ApiResponseDto<String> deleteGroceryItem(Long id);

	ApiResponseDto<GroceryItem> updateGroceryItem(Long id, GroceryItem updatedItem);

	ApiResponseDto<GroceryItem> updateInventory(Long id, BigInteger quantity);

}
