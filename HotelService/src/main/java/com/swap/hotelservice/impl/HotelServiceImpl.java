package com.swap.hotelservice.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swap.hotelservice.entities.Hotel;
import com.swap.hotelservice.exceptions.ResourceNotFoundException;
import com.swap.hotelservice.repositories.HotelRepository;
import com.swap.hotelservice.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		//generate unique id
		String randomId = UUID.randomUUID().toString();
		hotel.setId(randomId);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server!! " + id));
	}

	@Override
	public Hotel updateHotel(String id, Hotel hotel) {
		// TODO Auto-generated method stub
		 Hotel existingHotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server!! " + id));;
	     existingHotel.setName(hotel.getName()); 
	     existingHotel.setLocation(hotel.getLocation());
	     existingHotel.setAbout(hotel.getAbout());
	     Hotel updatedHotel = hotelRepository.save(existingHotel);
	     return updatedHotel;
	}

	@Override
	public void deleteHotel(String id) {
		// TODO Auto-generated method stub
		hotelRepository.deleteById(id);
	}

	
}
