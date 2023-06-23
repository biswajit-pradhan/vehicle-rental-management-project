package com.vehiclemodule.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclemodule.entity.Vehicle;
import com.vehiclemodule.exception.NoDriverFoundException;
import com.vehiclemodule.exception.VehicleNotFoundException;
import com.vehiclemodule.repository.DriverRepository;
import com.vehiclemodule.repository.VehicleRepository;
import com.vehiclemodule.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService{

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DriverRepository driverRepository;

	public List<Vehicle> getAllVehicles() {
		if (vehicleRepository.findAll().isEmpty()) {
			throw new NoDriverFoundException("No Vehicles Available!!");
		}
		return vehicleRepository.findAll();
	}

	public Vehicle getVehicleById(int vehicleId) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			throw new VehicleNotFoundException("Vehicle Not Found With This Id");
		}
		return vehicleRepository.findById(vehicleId).get();
	}

	public void addVehicle(Vehicle vehicle) {
		driverRepository.save(vehicle.getDriver());
		vehicleRepository.save(vehicle);
	}

	public void deleteVehicleById(int vehicleId) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			throw new VehicleNotFoundException("No Vehicle Found for Deletion!!");
		}
		vehicleRepository.deleteById(vehicleId);
	}

	public void updateVehicleById(int vehicleId, Vehicle vehicle) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			throw new VehicleNotFoundException("No Vehicle Found For This Id!!");
		}
		Vehicle vehicleData = vehicleRepository.findById(vehicleId).get();

		vehicleData.setVehicleNumber(vehicle.getVehicleNumber());
		vehicleData.setType(vehicle.getType());
		vehicleData.setCategory(vehicle.getCategory());
		vehicleData.setDescription(vehicle.getDescription());
		vehicleData.setLocation(vehicle.getLocation());
		vehicleData.setCapacity(vehicle.getCapacity());
		vehicleData.setChargesPerKM(vehicle.getChargesPerKM());
		vehicleData.setFixedCharges(vehicle.getFixedCharges());
		vehicleData.setDriver(vehicle.getDriver());
		
		driverRepository.save(vehicle.getDriver());
		vehicleRepository.save(vehicleData);
	}

}
