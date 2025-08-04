package com.example.service;

import com.example.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public void createUser(String name, String email) {
        // FIXED: Added input validation as requested in review
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        User user = new User(name, email);
        user.setId(generateId());
        users.put(user.getId(), user);
    }

    public Optional<User> getUser(String id) {
        // FIXED: Return Optional instead of null for better handling
        return Optional.ofNullable(users.get(id));
    }

    public boolean deleteUser(String id) {
        // FIXED: Check if user exists and return success status
        return users.remove(id) != null;
    }

    private String generateId() {
        return "USER_" + System.currentTimeMillis();
    }
}