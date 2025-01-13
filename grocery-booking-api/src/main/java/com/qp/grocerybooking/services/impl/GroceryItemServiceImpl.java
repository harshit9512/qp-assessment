package com.qp.grocerybooking.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.constants.ErrorMessages;
import com.qp.grocerybooking.constants.ResponseMessages;
import com.qp.grocerybooking.dto.GroceryItemDto;
import com.qp.grocerybooking.dto.request.UpdateGroceryItemRequestDto;
import com.qp.grocerybooking.dto.request.UpdateInventoryRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.services.GroceryItemService;

import jakarta.transaction.Transactional;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;
	
	@Autowired ModelMapper modelMapper;

	@Transactional
	public ApiResponseDto<GroceryItem> addGroceryItem(GroceryItemDto item) {
		GroceryItem groceryItem = modelMapper.map(item, GroceryItem.class);
		GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
		return ApiResponseDto.<GroceryItem>builder().isSuccess(true).message(ErrorMessages.ITEM_ADDED_SUCCESSFULLY)
				.data(savedGroceryItem).build();
	}

	public ApiResponseDto<List<GroceryItem>> getAllGroceryItems() {
		List<GroceryItem> groceryItems = groceryItemRepository.findAll();
		return ApiResponseDto.<List<GroceryItem>>builder().isSuccess(true)
				.message(ResponseMessages.DATA_FETCHED_SUCCESSFULLY).data(groceryItems).build();
	}

	@Transactional
	public ApiResponseDto<String> deleteGroceryItem(Long id) {
		Optional<GroceryItem> groceryItem = groceryItemRepository.findById(id);
		if (groceryItem.isEmpty()) {
			throw new ResourceNotFoundException(ErrorMessages.ITEM_DOES_NOT_EXIST);
		}
		groceryItemRepository.deleteById(id);
		return ApiResponseDto.<String>builder().isSuccess(true).message(ResponseMessages.ITEM_DELETED_SUCCESSFULLY)
				.build();
	}

	@Transactional
	public ApiResponseDto<GroceryItem> updateGroceryItem(Long id, UpdateGroceryItemRequestDto updatedItem) {
		GroceryItem item = groceryItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ITEM_DOES_NOT_EXIST));
		item.setName(updatedItem.getName());
		item.setPrice(updatedItem.getPrice());
		item.setDescription(updatedItem.getDescription());
		GroceryItem savedGroceryItem = groceryItemRepository.save(item);
		return ApiResponseDto.<GroceryItem>builder().isSuccess(true).message(ResponseMessages.ITEM_UPDATED_SUCCESSFULLY)
				.data(savedGroceryItem).build();
	}

	@Transactional
	public ApiResponseDto<GroceryItem> updateInventory(Long id, UpdateInventoryRequestDto updateInventoryRequestDto) {
		GroceryItem item = groceryItemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ITEM_DOES_NOT_EXIST));
		item.setQuantity(updateInventoryRequestDto.getQuantity());
		GroceryItem savedGroceryItem = groceryItemRepository.save(item);
		return ApiResponseDto.<GroceryItem>builder().isSuccess(true)
				.message(ResponseMessages.INVENTORY_UPDATED_SUCCESSFULLY).data(savedGroceryItem).build();
	}
}
