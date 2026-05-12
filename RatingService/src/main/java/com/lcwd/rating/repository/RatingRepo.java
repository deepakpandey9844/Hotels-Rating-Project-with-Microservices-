package com.lcwd.rating.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;  // Importing List to store the results of the custom queries

import com.lcwd.rating.entities.Rating;  // Importing Rating entity for the repository

public interface RatingRepo extends MongoRepository<Rating, String> {  // MongoRepository interface for CRUD operations

    // Custom finder methods for querying ratings by user ID and hotel ID
    
    // Finds a list of ratings by user ID
    List<Rating> findByUserId(String userId);  
    
    // Finds a list of ratings by hotel ID
    List<Rating> findByHotelId(String hotelId);
}
