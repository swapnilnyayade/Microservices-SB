package com.swap.userservice.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.swap.userservice.entities.Hotel;


@FeignClient(name = "HOTEL-SERVICE")
//provide runtime implementation automatically
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	Hotel obtainHotel(@PathVariable String hotelId);

}
