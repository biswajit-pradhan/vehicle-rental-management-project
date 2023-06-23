package com.vehiclemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehiclemodule.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
