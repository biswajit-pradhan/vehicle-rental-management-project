package com.vehiclemodule.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclemodule.entity.Vehicle;
import com.vehiclemodule.exception.NoDriverFoundException;
import com.vehiclemodule.exception.VehicleNotFoundException;
import com.vehiclemodule.repository.DriverRepository;
import com.vehiclemodule.repository.VehicleRepository;
import com.vehiclemodule.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private DriverRepository driverRepository;

	public List<Vehicle> getAllVehicles() {
		if (vehicleRepository.findAll().isEmpty()) {
			LOGGER.warn("No vehicles available");
			throw new NoDriverFoundException("No Vehicles Available!!");
		}
		return vehicleRepository.findAll();
	}

	public Vehicle getVehicleById(int vehicleId) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			LOGGER.warn("Vehicle not found for ID: {}", vehicleId);
			throw new VehicleNotFoundException("Vehicle Not Found With This Id");
		}
		return vehicleRepository.findById(vehicleId).get();
	}

	public void addVehicle(Vehicle vehicle) {
		driverRepository.save(vehicle.getDriver());
		vehicleRepository.save(vehicle);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Vehicle added: {}", vehicle);
		}
	}

	public void deleteVehicleById(int vehicleId) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			LOGGER.warn("No vehicle found for deletion with ID: {}", vehicleId);
			throw new VehicleNotFoundException("No Vehicle Found for Deletion!!");
		}
		vehicleRepository.deleteById(vehicleId);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Vehicle deleted with ID: {}", vehicleId);
		}
	}

	public void updateVehicleById(int vehicleId, Vehicle vehicle) {
		if (!vehicleRepository.findById(vehicleId).isPresent()) {
			LOGGER.warn("No vehicle found for update with ID: {}", vehicleId);
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
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Vehicle updated with ID: {}", vehicleId);
		}
	}
}
