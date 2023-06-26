package com.adminmodule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminmodule.entity.Vehicle;
import com.adminmodule.serviceImpl.AdminServiceImpl;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"*"})
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/addvehicle")
	public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle)  {
		
		try {
			adminService.addVehicle(vehicle);
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN:Vehicle details added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
	
	@PutMapping("/updatevehicle/{vehicleId}")
	public ResponseEntity<?> updateVehicleById(@Valid @RequestBody Vehicle vehicle, @PathVariable int vehicleId){
		try {
			adminService.updateVehicleById(vehicleId, vehicle);
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN:Vehicle Data Updated Successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
	
	@GetMapping("/getallvehicles")
	public ResponseEntity<?> getAllVehicles(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllVehicles());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
	
	@GetMapping("/getvehiclebyid/{vehicleId}")
	public ResponseEntity<?> getVehicleById(@PathVariable int vehicleId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.getVehicleById(vehicleId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
	
	@GetMapping("/viewAllCustomer")
	public ResponseEntity<?> viewAllCustomerDetails(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.viewAllCustomerDetails());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: No Customers Found in Database!!");
		}
	}

	@GetMapping("/getallbooking")
	public ResponseEntity<?> getAllBookings(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllBookings());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
	
	@GetMapping("/viewallbookingbycustomerid/{customerId}")
	public ResponseEntity<?> viewAllBookingByCustomerId(@PathVariable int customerId){

		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.viewAllBookingByCustomerId(customerId));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("ADMIN: "+e.getMessage());
		}
	}
}
