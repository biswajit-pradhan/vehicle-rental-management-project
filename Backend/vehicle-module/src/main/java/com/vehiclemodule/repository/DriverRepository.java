package com.vehiclemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehiclemodule.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
