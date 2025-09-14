package com.example.Lemme.Borrow.stuff.repository;


import com.example.Lemme.Borrow.stuff.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
