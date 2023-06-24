package com.bookingmodule.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookingmodule.entity.Booking;
import com.bookingmodule.exception.NoBookingsFoundException;
import com.bookingmodule.repository.BookingRepository;
import com.bookingmodule.repository.CustomerRepository;
import com.bookingmodule.repository.DriverRepository;
import com.bookingmodule.repository.VehicleRepository;

@Service
public class BookingServiceImpl {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	public List<Booking> getAllBookings(){
		if(bookingRepository.findAll().isEmpty()){
			throw new NoBookingsFoundException("No Bookings Found");
		}
		return bookingRepository.findAll();
	}
	
	public Booking getBookingById(int bookingId){
		if(!bookingRepository.findById(bookingId).isPresent()) {
			throw new NoBookingsFoundException("No Booking Found for Booking Id "+bookingId);
		}
		return bookingRepository.findById(bookingId).get();
	}
	
	public void addBooking(Booking booking){
		driverRepository.save(booking.getVehicle().getDriver());
		customerRepository.save(booking.getCustomer());		
		vehicleRepository.save(booking.getVehicle());
		bookingRepository.save(booking);
	}
	
	public void deleteBookingById(@PathVariable int bookingId){
		if(!bookingRepository.findById(bookingId).isPresent()) {
			throw new NoBookingsFoundException("No Booking Found for Booking Id "+bookingId);
		}
		bookingRepository.deleteById(bookingId);
	}
	
	public void updateBookingById(Booking booking,int bookingId){
		if(!bookingRepository.findById(bookingId).isPresent()) {
			throw new NoBookingsFoundException("No Booking Found for Booking Id "+bookingId);
		}
		
		Booking bookingData=bookingRepository.findById(bookingId).get();
		
		bookingData.setBookingDate(booking.getBookingDate());
		bookingData.setBookingDescription(booking.getBookingDescription());
		bookingData.setBookingTillDate(booking.getBookingTillDate());
		bookingData.setCustomer(booking.getCustomer());
		bookingData.setDistance(booking.getDistance());
		bookingData.setTotalCost(booking.getTotalCost());
		bookingData.setVehicle(booking.getVehicle());
		
		driverRepository.save(bookingData.getVehicle().getDriver());
		customerRepository.save(bookingData.getCustomer());
		vehicleRepository.save(bookingData.getVehicle());
		
		bookingRepository.save(bookingData);
		
	}
	
	@GetMapping("/viewallbookingbycustomerid/{customerId}")
	public List<Booking> viewAllBookingByCustomerId(int customerId){
		List<Booking> booking= bookingRepository.findAll()
				.stream().filter(b->b.getCustomer().getCustomerId()==customerId)
				.collect(Collectors.toList());
		
		if(booking.isEmpty()) {
			throw new NoBookingsFoundException("No Booking Found for Customer Id "+customerId);
		}
		
		return booking;
	}
	
	@GetMapping("/viewallbookingbydate")
	public List<Booking> viewAllBookingByDate(LocalDate date){

		List<Booking> booking=bookingRepository.findAll()
				.stream().filter(b->b.getBookingDate().equals(date))
				.collect(Collectors.toList());
		
		if(booking.isEmpty()) {
			throw new NoBookingsFoundException("No Booking Found for Date "+date);
		}
		
		return booking;
	}
	
	
}
