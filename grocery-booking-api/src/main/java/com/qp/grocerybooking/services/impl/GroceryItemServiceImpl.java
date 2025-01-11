package com.qp.grocerybooking.services.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.services.GroceryItemService;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public ApiResponseDto<GroceryItem> addGroceryItem(GroceryItem item) {
        GroceryItem savedGroceryItem = groceryItemRepository.save(item);
        return ApiResponseDto.<GroceryItem>builder().isSuccess(true).message(ResponseMessages.ITEM_ADDED_SUCCESSFULLY).data(savedGroceryItem).build();
    }

    public ApiResponseDto<List<GroceryItem>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        return ApiResponseDto.<List<GroceryItem>>builder().isSuccess(true).message(ResponseMessages.DATA_FETCHED_SUCCESSFULLY).data(groceryItems).build();
    }

    public ApiResponseDto<String> deleteGroceryItem(Long id) {
    	Optional<GroceryItem> groceryItem = groceryItemRepository.findById(id);
    	if(groceryItem.isEmpty()) {
    		throw new ResourceNotFoundException(ResponseMessages.ITEM_DOES_NOT_EXIST);
    	}
        groceryItemRepository.deleteById(id);
		return ApiResponseDto.<String>builder().isSuccess(true).message(ResponseMessages.ITEM_DELETED_SUCCESSFULLY).build();
    }

    public ApiResponseDto<GroceryItem> updateGroceryItem(Long id, GroceryItem updatedItem) {
    	GroceryItem item = groceryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessages.ITEM_DOES_NOT_EXIST));
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setDescription(updatedItem.getDescription());
        GroceryItem savedGroceryItem = groceryItemRepository.save(item);
        return ApiResponseDto.<GroceryItem>builder().isSuccess(true).message(ResponseMessages.ITEM_UPDATED_SUCCESSFULLY).data(savedGroceryItem).build();
    }

    public ApiResponseDto<GroceryItem> updateInventory(Long id, BigInteger quantity) {
        GroceryItem item = groceryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResponseMessages.ITEM_DOES_NOT_EXIST));
        item.setQuantity(quantity);
        GroceryItem savedGroceryItem = groceryItemRepository.save(item);
        return ApiResponseDto.<GroceryItem>builder().isSuccess(true).message(ResponseMessages.INVENTORY_UPDATED_SUCCESSFULLY).data(savedGroceryItem).build();
    }
}
