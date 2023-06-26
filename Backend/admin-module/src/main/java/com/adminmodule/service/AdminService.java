package com.adminmodule.service;

import java.util.List;

import javax.validation.Valid;

import com.adminmodule.entity.Booking;
import com.adminmodule.entity.Customer;
import com.adminmodule.entity.Vehicle;

public interface AdminService {
	public void addVehicle(Vehicle vehicle);
	public void updateVehicleById(int vehicleId, @Valid Vehicle vehicle);
	public List<Vehicle> getAllVehicles();
	public Vehicle getVehicleById(int vehicleId);
	public List<Customer> viewAllCustomerDetails();
	public List<Booking> getAllBookings();
	public List<Booking> viewAllBookingByCustomerId(int customerId);
}
