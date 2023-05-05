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
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> obtainHotel(@PathVariable String id){
		Hotel hotel = hotelService.getHotel(id) ;
		return ResponseEntity.ok(hotel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> upgradeHotel(@PathVariable String id, @RequestBody Hotel hotel){
		Hotel updatedHotel = hotelService.updateHotel(id, hotel);
		return ResponseEntity.ok(updatedHotel);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeHotel(@PathVariable String id){
		hotelService.deleteHotel(id);
		return ResponseEntity.ok("Hotel successfully deleted!");
	}



}
