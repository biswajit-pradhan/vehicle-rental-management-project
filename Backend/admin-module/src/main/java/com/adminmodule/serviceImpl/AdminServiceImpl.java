package com.adminmodule.serviceImpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adminmodule.entity.Booking;
import com.adminmodule.entity.Customer;
import com.adminmodule.entity.Vehicle;
import com.adminmodule.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private RestTemplate restTemplate;

	public void addVehicle(Vehicle vehicle) {
	    String url = "http://vehicle-module/api/vehicle/addvehicle";
	    this.restTemplate.postForEntity(url, vehicle, Void.class);
	}

	public void updateVehicleById(int vehicleId, @Valid Vehicle vehicle) {
		String url = "http://vehicle-module/api/vehicle/updatevehiclebyid/"+Integer.toString(vehicleId);
	    this.restTemplate.put(url, vehicle, Void.class);
		
	}

	public List<Vehicle> getAllVehicles() {
		String url = "http://vehicle-module/api/vehicle/getallvehicles";
	    List<Vehicle> vehicle= this.restTemplate.getForObject(url,List.class);
	    return vehicle;
	}

	public Vehicle getVehicleById(int vehicleId) {
		String url = "http://vehicle-module/api/vehicle/getvehiclebyid/"+Integer.toString(vehicleId);
	    Vehicle vehicle= this.restTemplate.getForObject(url,Vehicle.class);
	    return vehicle;
	}

	public List<Customer> viewAllCustomerDetails() {
		String url = "http://customer-module/api/customer/viewAllCustomer";
	    List<Customer> customer= this.restTemplate.getForObject(url,List.class);
	    return customer;
	}

	public List<Booking> getAllBookings() {
		String url = "http://booking-module/api/booking/getallbooking";
	    List<Booking> booking= this.restTemplate.getForObject(url,List.class);
	    return booking;
	}

	public List<Booking> viewAllBookingByCustomerId(int customerId) {
		String url = "http://booking-module/api/booking/viewallbookingbycustomerid/"+Integer.toString(customerId);
	    List<Booking> booking= this.restTemplate.getForObject(url,List.class);
	    return booking;
	}
	
	

}
