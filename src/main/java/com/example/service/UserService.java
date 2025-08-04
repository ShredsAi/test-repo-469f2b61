package com.example.service;

import com.example.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserService {
    private Map<String, User> users = new HashMap<>();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");

    public void createUser(String name, String email) {
        // FIXED: Added input validation as requested in review
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        // FIXED: Added email format validation
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // FIXED: Added duplicate email prevention
        if (users.values().stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("User with this email already exists");
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