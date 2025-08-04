package com.example.service;

import com.example.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Service class for managing users in the system.
 */
public class UserService {
    private Map<String, User> users = new HashMap<>();
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$");

    /**
     * Creates a new user with validation checks.
     * @param name User's name (cannot be null or empty)
     * @param email User's email (must be valid format and unique)
     * @throws IllegalArgumentException if validation fails
     */
    public void createUser(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (users.values().stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User user = new User(name, email);
        user.setId(generateId());
        users.put(user.getId(), user);
    }

    /**
     * Retrieves a user by ID.
     * @param id User ID to search for
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> getUser(String id) {
        return Optional.ofNullable(users.get(id));
    }

    /**
     * Deletes a user by ID.
     * @param id User ID to delete
     * @return true if user was deleted, false if user didn't exist
     */
    public boolean deleteUser(String id) {
        return users.remove(id) != null;
    }

    private String generateId() {
        return "USER_" + System.currentTimeMillis();
    }
}