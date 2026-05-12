package com.lcwd.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lcwd.user.service.entities.User; // Replace with your actual entity class

public interface UserRepository extends JpaRepository<User, String> {
    // You can define custom query methods here if needed
}
