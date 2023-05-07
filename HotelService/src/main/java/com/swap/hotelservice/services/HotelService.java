package com.swap.hotelservice.services;

import java.util.List;

import com.swap.hotelservice.entities.Hotel;

public interface HotelService {

	// hotel operations

	// create
	Hotel saveHotel(Hotel hotel);
	
	// get all hotel
	List<Hotel> getAllHotel();

	// get single hotel of given hotelId
	Hotel getHotel(String hotelId);

	Hotel updateHotel(String hotelId, Hotel hotel);

	void deleteHotel(String hotelId);
}
