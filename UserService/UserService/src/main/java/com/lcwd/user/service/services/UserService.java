package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;
import java.util.List;

public interface UserService {

    // Method to save a user
    User saveUser(User user);

    // Method to fetch all users
    List<User> getAllUser();

    // Method to fetch a user by ID
    User getUser(String userId);
}
