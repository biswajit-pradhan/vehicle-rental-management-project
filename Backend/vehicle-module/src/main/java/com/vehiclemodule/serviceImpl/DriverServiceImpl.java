package com.vehiclemodule.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclemodule.entity.Driver;
import com.vehiclemodule.exception.NoDriverFoundException;
import com.vehiclemodule.repository.DriverRepository;
import com.vehiclemodule.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Autowired
	private DriverRepository driverRepository;

	public List<Driver> getAllDrivers() {
		if (driverRepository.findAll().isEmpty()) {
			LOGGER.warn("No drivers available");
			throw new NoDriverFoundException("No Drivers Available!!");
		}
		return driverRepository.findAll();
	}

	public Driver getDriverById(int driverId) {
		if (!driverRepository.findById(driverId).isPresent()) {
			LOGGER.warn("No driver available for ID: {}", driverId);
			throw new NoDriverFoundException("No Driver Available On This Id!!");
		}
		return driverRepository.findById(driverId).get();
	}

	public void addDriver(Driver driver) {
		driverRepository.save(driver);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Driver added: {}", driver);
		}
	}

	public void deleteDriverById(int driverId) {
		if (!driverRepository.findById(driverId).isPresent()) {
			LOGGER.warn("No driver found for deletion with ID: {}", driverId);
			throw new NoDriverFoundException("No Driver Found for Deletion!!");
		}
		driverRepository.deleteById(driverId);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Driver deleted with ID: {}", driverId);
		}
	}

	public void updateDriverById(int driverId, Driver driver) {
		if (!driverRepository.findById(driverId).isPresent()) {
			LOGGER.warn("No driver found for update with ID: {}", driverId);
			throw new NoDriverFoundException("No Driver Found For This Id!!");
		}
		Driver driverData = driverRepository.findById(driverId).get();

		driverData.setFirstName(driver.getFirstName());
		driverData.setLastName(driver.getLastName());
		driverData.setMobileNumber(driver.getMobileNumber());
		driverData.setEmailId(driver.getEmailId());
		driverData.setLicenceNo(driver.getLicenceNo());
		driverData.setAddress(driver.getAddress());

		driverRepository.save(driverData);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Driver updated with ID: {}", driverId);
		}
	}
}
