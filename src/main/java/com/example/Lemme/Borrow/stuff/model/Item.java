package com.example.Lemme.Borrow.stuff.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "items")
public class Item {
    // Getters and Setters
    @Id
    private String id;

    private String name;
    private String description;
    private String ownerId;

    // IMPORTANT: Getter name must match Spring Data convention
    private boolean available = true; // changed field name

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public void setAvailable(boolean available) { this.available = available; }
}
