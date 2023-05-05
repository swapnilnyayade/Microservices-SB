package com.swap.hotelservice.services;

import java.util.List;

import com.swap.hotelservice.entities.Hotel;

public interface HotelService {

	// hotel operations

	// create
	Hotel saveHotel(Hotel hotel);
	
	// get all hotel
	List<Hotel> getAllHotel();

	// get single hotel of given id
	Hotel getHotel(String id);

	Hotel updateHotel(String id, Hotel hotel);

	void deleteHotel(String id);
}
