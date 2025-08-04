package com.example.service;

import com.example.entity.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public void createUser(String name, String email) {
        User user = new User(name, email);
        user.setId(generateId());
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public void deleteUser(String id) {
        users.remove(id);
    }

    private String generateId() {
        return "USER_" + System.currentTimeMillis();
    }
}