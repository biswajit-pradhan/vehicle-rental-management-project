package com.bookingmodule.service;

import java.time.LocalDate;
import java.util.List;

import com.bookingmodule.entity.Booking;

public interface BookingService {
	public List<Booking> getAllBookings();
	public Booking getBookingById(int bookingId);
	public void addBooking(Booking booking);
	public void deleteBookingById(int bookingId);
	public void updateBookingById(Booking booking,int bookingId);
	public List<Booking> viewAllBookingByCustomerId(int customerId);
	public List<Booking> viewAllBookingByDate(LocalDate date);

}
