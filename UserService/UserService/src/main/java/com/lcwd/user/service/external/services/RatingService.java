package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {

	
	
	@PostMapping("/ratings/create")
	public Rating createRating(Rating values);
	
	
	
	@PostMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);
	
	
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
	
	
	
}
