package com.swap.hotelservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swap.hotelservice.entities.Hotel;
import com.swap.hotelservice.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel createdHotel = hotelService.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> allHotel = hotelService.getAllHotel() ;
		return ResponseEntity.ok(allHotel);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> obtainHotel(@PathVariable String hotelId){
		Hotel hotel = hotelService.getHotel(hotelId) ;
		return ResponseEntity.ok(hotel);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> upgradeHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
		Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
		return ResponseEntity.ok(updatedHotel);
	}

	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> removeHotel(@PathVariable String hotelId){
		hotelService.deleteHotel(hotelId);
		return ResponseEntity.ok("Hotel successfully deleted!");
	}



}
