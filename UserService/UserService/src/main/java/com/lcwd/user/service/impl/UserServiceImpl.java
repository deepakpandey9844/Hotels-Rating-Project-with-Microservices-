package com.lcwd.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HotelService hotelService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // Generate a random UUID for the user
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userId) {
    	// Step 1: Fetch user by ID from the database
        // If the user does not exist, throw a ResourceNotFoundException
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));
        // Step 2: Define a hardcoded URL for external service integration
        // Note: This URL is for testing purposes and should be replaced with a dynamic value in production
        String url = "http://RATINGSERVICE/ratings/users/";
        // Step 3: Use RestTemplate to fetch external data
        // The data is expected to be a list of "Rating" objects
        Rating[] response = restTemplate.getForObject(url+user.getUserId(), Rating[].class);
        
        List<Rating> ratings= Arrays.stream(response).toList();
     	System.out.println("ceckign what is the id of hotel"+ratings);
        List<Rating> ratinglist = ratings.stream().map(rating->{
        	
        	System.out.println("ceckign what is the id of hotel"+rating.getHotelid());
        //	ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelid(), Hotel.class);
        	Hotel hotel = hotelService.gethotel(rating.getHotelid());
        	 // logger.info("response status code: {}", forEntity.getStatusCode());
        	  rating.setHotel(hotel);
        	  return rating;
        }).collect(Collectors.toList());
        
        
        // Step 4: Log the retrieved data for debugging or auditing purposes
        logger.info("External data retrieved for user {}: {}", userId, response);
        // Step 5: Enrich the user object with the retrieved data
        // Assuming "setRatings" is a method in the User entity to add the fetched ratings
        user.setRatings(ratinglist);
        // Step 6: Return the enriched user object
        return user;

    }

    @Override
    public List<User> getAllUser() {
    	// Step 1: Retrieve all users from the repository
        List<User> users = userRepository.findAll();

        // Step 2: Iterate through each user to fetch external data
        users.forEach(user -> {
            // Define the external service URL dynamically for each user
            String url = "http://RATINGSERVICE/ratings/users/" + user.getUserId();

            // Fetch external data using RestTemplate
            ArrayList<Rating> response = restTemplate.getForObject(url, ArrayList.class);

            // Log the retrieved data
            logger.info("External data retrieved for user {}: {}", user.getUserId(), response);

            // Enrich the user object with the retrieved data
            user.setRatings(response);
        });

        // Step 3: Return the enriched list of users
        return users;
    }


}
