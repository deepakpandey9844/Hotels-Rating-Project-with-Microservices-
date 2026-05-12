package com.lcwd.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repositories.HotelRepo;
import com.lcwd.hotel.services.HotelServices;

import com.lcwd.hotel.exceptions.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelRepo hotelRepository;
    
    @Override
    public Hotel create(Hotel hotel) {
    	
    	String hotelid=UUID.randomUUID().toString();
	    hotel.setId(hotelid);
	    return hotelRepository.save(hotel);
    	
        // Save and return the newly created hotel
        //hotel is here is entity to it know what are the id, name location, about
        //so no need to save anything just save hotel.
    }

    @Override
    public List<Hotel> getAllHotels() {
        // Fetch and return all hotels
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        // Find hotel by id or throw ResourceNotFoundException if not found
        return hotelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel with the given id not found"));
    }
}
