package com.lcwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Default constructor with a predefined error message
    public ResourceNotFoundException() {
        super("Resource not found on server !!");
    }

    // Constructor that accepts a custom message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
