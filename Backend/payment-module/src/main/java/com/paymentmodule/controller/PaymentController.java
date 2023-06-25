package com.paymentmodule.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymentmodule.dto.PaymentDto;
import com.paymentmodule.dto.VehicleDto;
import com.paymentmodule.entity.Booking;
import com.paymentmodule.entity.Payment;
import com.paymentmodule.entity.Vehicle;
import com.paymentmodule.exception.NotFoundException;
import com.paymentmodule.service.PaymentServiceImpl;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;

	@PostMapping("/addPayment")
	public ResponseEntity<Payment> addPayment(@RequestBody PaymentDto payment) throws NotFoundException {
		Payment addedPayment = paymentServiceImpl.addPayment(payment);
		return new ResponseEntity<>(addedPayment, HttpStatus.CREATED);
	}

	@DeleteMapping("/cancelPayment")
	public ResponseEntity<Payment> cancelPayment(@RequestBody PaymentDto payment) throws NotFoundException {
		Payment cancelPayment=paymentServiceImpl.cancelPayment(payment);
		return new ResponseEntity<>(cancelPayment, HttpStatus.OK);
	}

	@GetMapping("/viewpayment")
	public ResponseEntity<Payment> viewPayment(@RequestParam int bookingId) throws NotFoundException {
		Payment payment1 = paymentServiceImpl.viewPayment(bookingId);
		return new ResponseEntity<>(payment1, HttpStatus.OK);
	}

	@GetMapping("/viewAllPayment")
	public ResponseEntity<List<Payment>> viewAllPayment() {
		List<Payment> payments = paymentServiceImpl.viewAllPayment();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}

	@GetMapping("/revenue")
	public ResponseEntity<Double> calculateMonthlyRevenue(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		LocalDate start = LocalDate.parse(startDate);
	    LocalDate end = LocalDate.parse(endDate);
		double totalRevenue = paymentServiceImpl.calculateMonthlyRevenue(start, end);
		return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
	}

	@GetMapping("/totalpayment")
	public ResponseEntity<Double> calculateTotalPayment(@RequestParam int vehicleId) {
		double totalPayment = paymentServiceImpl.calculateTotalPayment(vehicleId);
		return new ResponseEntity<>(totalPayment, HttpStatus.OK);
	}
}
