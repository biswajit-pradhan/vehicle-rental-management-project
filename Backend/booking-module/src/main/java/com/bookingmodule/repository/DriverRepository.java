package com.bookingmodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingmodule.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{

}
