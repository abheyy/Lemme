package com.example.Lemme.Borrow.stuff.controller;


import com.example.Lemme.Borrow.stuff.model.Item;
import com.example.Lemme.Borrow.stuff.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAvailableItems() {
        return ResponseEntity.ok(itemService.getAvailableItems());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Item>> getItemsByOwner(@PathVariable String ownerId) {
        return ResponseEntity.ok(itemService.getItemsByOwner(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Item> updateAvailability(@PathVariable String id,
                                                   @RequestParam boolean available) {
        return ResponseEntity.ok(itemService.updateAvailability(id, available));
    }
}
