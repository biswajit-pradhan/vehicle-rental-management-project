package com.bookingmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingmodule.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
