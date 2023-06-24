package com.bookingmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingmodule.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
