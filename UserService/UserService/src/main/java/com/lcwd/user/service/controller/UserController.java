package com.lcwd.user.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Constructor-based Dependency Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
   int reTryCount=1;
    @GetMapping("/{userId}")
  //  @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
  //  @Retry(name="ratingHotelService", fallbackMethod ="ratingHotelFallBack")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
    	logger.info("retry count {} ",reTryCount);
    	
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {
        logger.info("Fallback method executed for User ID: {} due to exception: {}", userId, ex.getMessage());

        // Return the custom fallback user message
        User fallbackUser = User.builder()
            .userId("1234")
            .email("dummy@gmail.com")
            .name("Dummy User")
            .about("The service is temporarily unavailable. Please try again later.")
            .build();

        return ResponseEntity.ok(fallbackUser);  // Return the fallback user response
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
}
