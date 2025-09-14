package com.example.Lemme.Borrow.stuff.repository;

import com.example.Lemme.Borrow.stuff.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByAvailableTrue();
    List<Item> findByOwnerId(String ownerId);
}
