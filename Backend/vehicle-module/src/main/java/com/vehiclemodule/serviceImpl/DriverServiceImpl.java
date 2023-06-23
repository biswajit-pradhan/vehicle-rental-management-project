package com.vehiclemodule.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehiclemodule.entity.Driver;
import com.vehiclemodule.exception.NoDriverFoundException;
import com.vehiclemodule.repository.DriverRepository;
import com.vehiclemodule.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverRepository driverRepository;

	public List<Driver> getAllDrivers() {
		if (driverRepository.findAll().isEmpty()) {
			throw new NoDriverFoundException("No Drivers Available!!");
		}
		return driverRepository.findAll();
	}

	public Driver getDriverById(int driverId) {
		if (!driverRepository.findById(driverId).isPresent()) {
			throw new NoDriverFoundException("No Driver Available On This Id!!");
		}
		return driverRepository.findById(driverId).get();
	}

	public void addDriver(Driver driver) {
		driverRepository.save(driver);
	}

	public void deleteDriverById(int driverId) {
		if (!driverRepository.findById(driverId).isPresent()) {
			throw new NoDriverFoundException("No Driver Found for Deletion!!");
		}
		driverRepository.deleteById(driverId);
	}

	public void updateDriverById(int driverId, Driver driver) {
		if (!driverRepository.findById(driverId).isPresent()) {
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
	}

}
