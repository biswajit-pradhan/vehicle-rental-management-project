package com.vehiclemodule.controller;

import javax.validation.Valid;

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

import com.vehiclemodule.entity.Vehicle;
import com.vehiclemodule.serviceImpl.VehicleServiceImpl;

@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = { "*" })
public class VehicleController {

	@Autowired
	private VehicleServiceImpl vehicleServiceImpl;

	@GetMapping("/getallvehicles")
	public ResponseEntity<?> getAllVehicles() {
		return ResponseEntity.status(HttpStatus.OK).body(vehicleServiceImpl.getAllVehicles());
	}

	@GetMapping("/getvehiclebyid/{vehicleId}")
	public ResponseEntity<?> getVehicleById(@PathVariable int vehicleId) {
		return ResponseEntity.status(HttpStatus.OK).body(vehicleServiceImpl.getVehicleById(vehicleId));
	}

	@PostMapping("/addvehicle")
	public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle) {
		vehicleServiceImpl.addVehicle(vehicle);
		return ResponseEntity.status(HttpStatus.OK).body("Vehicle details added successfully");
	}

	@DeleteMapping("/deletevehiclebyid/{vehicleId}")
	public ResponseEntity<?> deleteVehicleById(@PathVariable int vehicleId) {
		vehicleServiceImpl.deleteVehicleById(vehicleId);
		return ResponseEntity.status(HttpStatus.OK).body("Vehicle Details Deleted Successfully");
	}

	@PutMapping("/updatevehiclebyid/{vehicleId}")
	public ResponseEntity<?> updateVehicleById(@Valid @RequestBody Vehicle vehicle, @PathVariable int vehicleId) {
		vehicleServiceImpl.updateVehicleById(vehicleId, vehicle);
		return ResponseEntity.status(HttpStatus.OK).body("Vehicle Data Updated Successfully");
	}

}
