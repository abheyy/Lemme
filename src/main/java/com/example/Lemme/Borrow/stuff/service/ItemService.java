package com.example.Lemme.Borrow.stuff.service;


import com.example.Lemme.Borrow.stuff.model.Item;
import com.example.Lemme.Borrow.stuff.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // Add a new item
    public Item addItem(Item item) {
        item.setAvailable(true);
        return itemRepository.save(item);
    }

    // Get all available items
    public List<Item> getAvailableItems() {
        return itemRepository.findByAvailableTrue();
    }

    // Get all items by owner
    public List<Item> getItemsByOwner(String ownerId) {
        return itemRepository.findByOwnerId(ownerId);
    }

    // Get item by ID
    public Optional<Item> getItemById(String id) {
        return itemRepository.findById(id);
    }

    // Update item availability
    public Item updateAvailability(String itemId, boolean isAvailable) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setAvailable(isAvailable);
        return itemRepository.save(item);
    }
}
