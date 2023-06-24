package com.vehiclemodule.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehiclemodule.entity.Driver;
import com.vehiclemodule.serviceImpl.DriverServiceImpl;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = {"*"})
public class DriverController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverController.class);
	
	@Autowired
	private DriverServiceImpl driverServiceImpl;
	
	@GetMapping("/getalldrivers")
	public ResponseEntity<?> getAllDrivers(){
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Getting all drivers");
		}
		return ResponseEntity.status(HttpStatus.OK).body(driverServiceImpl.getAllDrivers());
	}
	
	@GetMapping("/getdriverbyid/{driverId}")
	public ResponseEntity<?> getDriverById(@PathVariable int driverId){
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Getting driver by ID: {}", driverId);
		}
		return ResponseEntity.status(HttpStatus.OK).body(driverServiceImpl.getDriverById(driverId));
	}
	
	@PostMapping("/adddriver")
	public ResponseEntity<?> addDriver(@Valid @RequestBody Driver driver){
		driverServiceImpl.addDriver(driver);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Added driver details: {}", driver);
			}
		return ResponseEntity.status(HttpStatus.OK).body("Driver details added successfully");
	}
	
	@DeleteMapping("/deletedriverbyid/{driverId}")
	public ResponseEntity<?> deleteDriverById(@PathVariable int driverId){
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Deleting driver by ID: {}", driverId);
		}
		driverServiceImpl.deleteDriverById(driverId);
		return ResponseEntity.status(HttpStatus.OK).body("Driver Details Deleted Successfully");
	}
	
	@PutMapping("/updatedriver/{driverId}")
	public ResponseEntity<?> updateDriverById(@Valid @RequestBody Driver driver,@PathVariable int driverId){
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Updating driver by ID: {}, new driver data: {}", driverId, driver);
		}
		driverServiceImpl.updateDriverById(driverId,driver);
		return ResponseEntity.status(HttpStatus.OK).body("Driver Data Updated Successfully");
	}
}
