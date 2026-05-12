package com.lcwd.hotel.services;

import java.util.List;
import java.util.UUID;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repositories.HotelRepo;

public interface HotelServices {
	 

	    // Create a new hotel
	    public Hotel create(Hotel hotel);
	
	    // Get all hotels
	    public List<Hotel> getAllHotels();
	    
	    // Get a single hotel by ID
	    public Hotel getHotelById(String id) ;
	    
	    
	    }
