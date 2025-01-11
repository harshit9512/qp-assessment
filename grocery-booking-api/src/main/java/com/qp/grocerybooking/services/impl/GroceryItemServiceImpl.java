package com.qp.grocerybooking.services.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.grocerybooking.entities.GroceryItem;
import com.qp.grocerybooking.exceptions.ResourceNotFoundException;
import com.qp.grocerybooking.repositories.GroceryItemRepository;
import com.qp.grocerybooking.services.GroceryItemService;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryItem addGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public void deleteGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem) {
    	GroceryItem item = groceryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setDescription(updatedItem.getDescription());
        return groceryItemRepository.save(item);
    }

    public GroceryItem updateInventory(Long id, BigInteger quantity) {
        GroceryItem item = groceryItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        item.setQuantity(quantity);
        return groceryItemRepository.save(item);
    }
}
