package com.vehiclemodule.service;

import java.util.List;

import com.vehiclemodule.entity.Driver;

public interface DriverService {
	public List<Driver> getAllDrivers();
	public Driver getDriverById(int driverId);
	public void addDriver(Driver driver);
	public void deleteDriverById(int driverId);
	public void updateDriverById(int driverId, Driver driver);
}
