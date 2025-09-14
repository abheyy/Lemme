package com.example.Lemme.Borrow.stuff.service;



import com.example.Lemme.Borrow.stuff.model.User;
import com.example.Lemme.Borrow.stuff.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        // You can hash password here before saving
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Find user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
