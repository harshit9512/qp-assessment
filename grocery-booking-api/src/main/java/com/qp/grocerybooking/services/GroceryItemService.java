package com.qp.grocerybooking.services;

import java.util.List;
import com.qp.grocerybooking.dto.GroceryItemDto;
import com.qp.grocerybooking.dto.request.UpdateGroceryItemRequestDto;
import com.qp.grocerybooking.dto.request.UpdateInventoryRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;

public interface GroceryItemService {

	ApiResponseDto<GroceryItem> addGroceryItem(GroceryItemDto item);

	ApiResponseDto<List<GroceryItem>> getAllGroceryItems();

	ApiResponseDto<String> deleteGroceryItem(Long id);

	ApiResponseDto<GroceryItem> updateGroceryItem(Long id, UpdateGroceryItemRequestDto updatedItem);

	ApiResponseDto<GroceryItem> updateInventory(Long id, UpdateInventoryRequestDto updateInventoryRequestDto);

}
