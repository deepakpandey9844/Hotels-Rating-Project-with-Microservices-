package com.lcwd.hotel.exceptions;

import java.util.HashMap;  // Importing HashMap to create a response body
import java.util.Map;      // Importing Map to store response data

import org.springframework.http.HttpStatus;  // Importing HttpStatus to specify HTTP status codes
import org.springframework.http.ResponseEntity;  // Importing ResponseEntity to return custom responses
import org.springframework.web.bind.annotation.ExceptionHandler;  // Import to handle exceptions
import org.springframework.web.bind.annotation.RestControllerAdvice;  // Import for global exception handling

@RestControllerAdvice  // Annotates the class to handle exceptions globally for all controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)  // Specifies that this method handles ResourceNotFoundException
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Create a custom response map to send in the response body
        Map<String, Object> map = new HashMap<>();  // Initialize a HashMap to hold response data
        
        // Add the exception message to the map
        map.put("message", ex.getMessage());
        
        // Indicate that the request was unsuccessful
        map.put("success", false);
        
        // Set the HTTP status to 404 (Not Found)
        map.put("status", HttpStatus.NOT_FOUND.value());

        // Return the response with HTTP status 404 and the custom error response map
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
