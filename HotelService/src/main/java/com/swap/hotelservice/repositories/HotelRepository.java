package com.swap.hotelservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swap.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
