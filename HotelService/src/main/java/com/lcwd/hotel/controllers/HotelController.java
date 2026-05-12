package com.lcwd.hotel.controllers;

import java.util.List;  // Import List class for returning multiple hotels

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

import com.lcwd.hotel.entities.Hotel;  // Import the Hotel entity
import com.lcwd.hotel.services.HotelServices;  // Import the service layer for hotel business logic

@RestController  // Indicates that this class is a REST controller and will return JSON responses
@RequestMapping("/hotels")  // Base URL path for all the methods in this controller (e.g., /hotels)
public class HotelController {

    @Autowired
    private HotelServices hotelService;  // Automatically inject the HotelServices to handle business logic for hotels

    
    
    
    @PreAuthorize("hasAuthoriy('Admin')")
    // Create a new hotel
    @PostMapping("/create")  // Maps the POST request with "/create" endpoint to this method
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED)  // Return status code 201 (Created)
                .body(hotelService.create(hotel));  // Call the service's create method to save the hotel and return it in the response body
    }

    
    @PreAuthorize("hasAuthoriy('SCOPE_Internal')")
    // Get a single hotel by ID
    @GetMapping("/{id}")  // Maps the GET request with a dynamic ID parameter to this method
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)  // Return status code 200 (OK)
                .body(hotelService.getHotelById(id));  // Call the service's method to fetch the hotel and return it in the response body
    }

    
    @PreAuthorize("hasAuthoriy('SCOPE_Internal') || hasAuthority('Admin')")
    // Get all hotels
    @GetMapping("/getAll")  // Maps the GET request with "/getAll" endpoint to this method
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());  // Return status code 200 (OK) and the list of all hotels in the response body
    }
}
