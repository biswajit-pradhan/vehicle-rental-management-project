package com.vehiclemodule.service;

import java.util.List;

import com.vehiclemodule.entity.Vehicle;

public interface VehicleService {
	public List<Vehicle> getAllVehicles();
	public Vehicle getVehicleById(int vehicleId);
	public void addVehicle(Vehicle vehicle);
	public void deleteVehicleById(int vehicleId);
	public void updateVehicleById(int vehicleId, Vehicle vehicle);
}
