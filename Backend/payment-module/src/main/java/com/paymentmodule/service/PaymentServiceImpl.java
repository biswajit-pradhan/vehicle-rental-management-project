package com.paymentmodule.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentmodule.dto.PaymentDto;
import com.paymentmodule.dto.VehicleDto;
import com.paymentmodule.entity.Booking;
import com.paymentmodule.entity.Payment;
import com.paymentmodule.entity.Vehicle;
import com.paymentmodule.exception.NotFoundException;
import com.paymentmodule.repository.BookingRepository;
import com.paymentmodule.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public Payment addPayment(PaymentDto payment) throws NotFoundException {
		// TODO Auto-generated method stub
		Optional<Payment> b1 = paymentRepo.findById(payment.getPaymentId());
		if (b1.isPresent()) {
			throw new NotFoundException("Payment Already Exists with id " + payment.getPaymentId());
		}
		Optional<Booking> optionalBooking = bookingRepo.findById(payment.getBookingId());
		if (optionalBooking.isEmpty()) {
			throw new NotFoundException("Booking not found with id " + payment.getBookingId());
		}

		Booking booking = optionalBooking.get();
		Payment p = new Payment();
		p.setPaymentId(payment.getPaymentId());
		p.setPaymentDate(payment.getPaymentDate());
		p.setPaymentMode(payment.getPaymentMode());
		p.setPaymentStatus(payment.getPaymentStatus());
		p.setBooking(booking);
		return paymentRepo.save(p);
	}

	@Override
	public Payment cancelPayment(PaymentDto payment) throws NotFoundException {
		// TODO Auto-generated method stub
		Optional<Payment> p = this.paymentRepo.findById(payment.getPaymentId());
		Optional<Booking> optionalBooking = bookingRepo.findById(payment.getBookingId());
		if (p.isEmpty()) {
			throw new NotFoundException("Payment does not  Exists with id " + payment.getPaymentId());
		}
		if (optionalBooking.isEmpty()) {
			throw new NotFoundException("Booking not found with id " + payment.getBookingId());
		}
		Payment existingPayment =p.get();
		Booking booking=optionalBooking.get();
		  if (existingPayment.getBooking() == null || booking.getBookingId()!=existingPayment.getBooking().getBookingId()) {
			throw new NotFoundException("not matching");
		}
			
			paymentRepo.deleteById(payment.getPaymentId());
			return existingPayment;
		
	}

	@Override
	public Payment viewPayment(Payment payment) throws NotFoundException {
		// TODO Auto-generated method stub
		Optional<Payment> payment1 = paymentRepo.findById(payment.getPaymentId());
		if (payment1.isPresent()) {
			return paymentRepo.findById(payment.getPaymentId()).get();
		} else {
			throw new NotFoundException("Booking not found");
		}
	}

	@Override
	public List<Payment> viewAllPayment() {
		// TODO Auto-generated method stub
		List<Payment> payments = paymentRepo.findAll();
		return payments;
	}

	@Override
	public double calculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		double totalRevenue = paymentRepo.calculateMonthRevenue(d1, d2);
		return totalRevenue;
	}

	@Override
	public Double calculateTotalPayment(int vehicleId) {
		// TODO Auto-generated method stub
	    Double totalPayment = paymentRepo.calculateTotalPaymentByVehicleId(vehicleId);
	    return totalPayment;
	}

}
