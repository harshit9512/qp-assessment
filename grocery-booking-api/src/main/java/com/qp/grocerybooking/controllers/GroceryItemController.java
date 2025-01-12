package com.qp.grocerybooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.grocerybooking.constants.ApiEndpoints;
import com.qp.grocerybooking.dto.GroceryItemDto;
import com.qp.grocerybooking.dto.request.UpdateGroceryItemRequestDto;
import com.qp.grocerybooking.dto.request.UpdateInventoryRequestDto;
import com.qp.grocerybooking.dto.response.ApiResponseDto;
import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.services.GroceryItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiEndpoints.GROCERY_ITEM_BASE_URL)
public class GroceryItemController {

	@Autowired
	private GroceryItemService groceryItemService;

	@PostMapping
	public ResponseEntity<ApiResponseDto<GroceryItem>> addGroceryItem(@Valid @RequestBody GroceryItemDto item) {
		return ResponseEntity.ok(groceryItemService.addGroceryItem(item));
	}

	@GetMapping
	public ResponseEntity<ApiResponseDto<List<GroceryItem>>> getAllGroceryItems() {
		return ResponseEntity.ok(groceryItemService.getAllGroceryItems());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGroceryItem(@PathVariable("id") @NonNull Long id) {
		groceryItemService.deleteGroceryItem(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDto<GroceryItem>> updateGroceryItem(@PathVariable("id") Long id,
			@Valid @RequestBody UpdateGroceryItemRequestDto updatedItem) {
		return ResponseEntity.ok(groceryItemService.updateGroceryItem(id, updatedItem));
	}

	@PatchMapping("/{id}/inventory")
	public ResponseEntity<ApiResponseDto<GroceryItem>> updateInventory(@PathVariable("id") Long id,
			@Valid @RequestBody UpdateInventoryRequestDto updateInventoryRequestDto) {
		return ResponseEntity.ok(groceryItemService.updateInventory(id, updateInventoryRequestDto));
	}
}
