package com.bookingmodule.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingmodule.entity.Booking;
import com.bookingmodule.serviceImpl.BookingServiceImpl;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = {"*"})
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingService;
	
	@GetMapping("/getallbooking")
	public ResponseEntity<?> getAllBookings(){
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
	}
	
	@GetMapping("/getbookingbyid/{bookingId}")
	public ResponseEntity<?> getBookingById(@PathVariable int bookingId){
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(bookingId));
	}
	
	@PostMapping("/addbooking")
	public ResponseEntity<?> addBooking(@Valid @RequestBody Booking booking){
		bookingService.addBooking(booking);
		return ResponseEntity.status(HttpStatus.OK).body("You have successfully booked");
	}
	
	@DeleteMapping("/deletebookingbyid/{bookingId}")
	public ResponseEntity<?> deleteBookingById(@PathVariable int bookingId){
		bookingService.deleteBookingById(bookingId);
		return ResponseEntity.status(HttpStatus.OK).body("Booking Canceled Successfully");
	}
	
	@PutMapping("/updatebookingbyid/{bookingId}")
	public ResponseEntity<?> updateBookingById(@Valid @RequestBody Booking booking,@PathVariable int bookingId){
		bookingService.updateBookingById(booking,bookingId);
		return ResponseEntity.status(HttpStatus.OK).body("Booking Data Updated Successfully!!");
	}
	
	@GetMapping("/viewallbookingbycustomerid/{customerId}")
	public ResponseEntity<?> viewAllBookingByCustomerId(@PathVariable int customerId){
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.viewAllBookingByCustomerId(customerId));
	}
	
	@GetMapping("/viewallbookingbydate")
	public ResponseEntity<?> viewAllBookingByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.viewAllBookingByDate(date));
	}
	
}
