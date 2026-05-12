package com.lcwd.rating.controller;

import java.util.List;  // Import List class for returning multiple ratings

import org.springframework.beans.factory.annotation.Autowired;  // Import to automatically inject dependencies
import org.springframework.http.HttpStatus;  // Correct import for HttpStatus enum
import org.springframework.http.ResponseEntity;  // Import for creating HTTP responses
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;  // Import to handle GET requests
import org.springframework.web.bind.annotation.PathVariable;  // Import to extract variables from URL path
import org.springframework.web.bind.annotation.PostMapping;  // Import to handle POST requests
import org.springframework.web.bind.annotation.RequestBody;  // Import to bind the request body to a method parameter
import org.springframework.web.bind.annotation.RequestMapping;  // Import to map HTTP requests to handler methods
import org.springframework.web.bind.annotation.RestController;  // Import to define the controller as a RESTful controller

import com.lcwd.rating.entities.Rating;  // Import the Rating entity
import com.lcwd.rating.services.RatingService;  // Import the service layer for rating business logic

@RestController  // Indicates that this class is a REST controller and will return JSON responses
@RequestMapping("/ratings")  // Base URL path for all the methods in this controller (e.g., /ratings)
public class RatingController {

    @Autowired
    private RatingService ratingService;  // Automatically inject the RatingService to handle business logic for ratings

    
    
    @PreAuthorize("hasAuthority('Admin')")
    // Create a new rating
    @PostMapping("/create")  // Maps the POST request with "/create" endpoint to this method
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED)  // Return status code 201 (Created)
                .body(ratingService.create(rating));  // Call the service's create method to save the rating and return it in the response body
    }

    @PreAuthorize("hasAuthoriy('SCOPE_Internal') || hasAuthority('Admin')")
    // Get all ratings
    @GetMapping("/getAll")  // Maps the GET request with "/getAll" endpoint to this method
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getRatings());  // Return status code 200 (OK) and the list of all ratings in the response body
    }
    @PreAuthorize("hasAuthoriy('SCOPE_Internal') || hasAuthority('Admin')")
    // Get ratings by user ID
    @GetMapping("/users/{userId}")  // Maps the GET request with "/user/{userId}" endpoint to this method
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));  // Return status code 200 (OK) and ratings filtered by user ID
    }
    @PreAuthorize("hasAuthoriy('SCOPE_Internal') || hasAuthority('Admin')")
    // Get ratings by hotel ID
    @GetMapping("/hotels/{hotelId}")  // Maps the GET request with "/hotel/{hotelId}" endpoint to this method
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));  // Return status code 200 (OK) and ratings filtered by hotel ID
    }
}
