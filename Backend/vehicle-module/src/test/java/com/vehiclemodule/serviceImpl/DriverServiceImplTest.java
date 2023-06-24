package com.vehiclemodule.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehiclemodule.entity.Driver;
import com.vehiclemodule.exception.NoDriverFoundException;
import com.vehiclemodule.repository.DriverRepository;

class DriverServiceImplTest {

	@Mock
	private DriverRepository driverRepository;

	@InjectMocks
	private DriverServiceImpl driverService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testGetAllDriversWhenNoDriversExist() {
        when(driverRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NoDriverFoundException.class, () -> {
            driverService.getAllDrivers();
        });

        verify(driverRepository, times(1)).findAll();
    }

	@Test
	void testGetDriverByIdWhenDriverDoesNotExist() {
		int driverId = 1;

		when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

		assertThrows(NoDriverFoundException.class, () -> {
			driverService.getDriverById(driverId);
		});

		verify(driverRepository, times(1)).findById(driverId);
	}

	@Test
	void testGetDriverByIdWhenDriverExist() {
		int driverId = 1;
		Driver driver = new Driver();

		when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

		Driver result = driverService.getDriverById(driverId);

		verify(driverRepository, times(2)).findById(driverId);
		assertEquals(driver, result);
	}

	@Test
	void testAddDriver() {
		Driver driver = new Driver();

		driverService.addDriver(driver);

		verify(driverRepository, times(1)).save(driver);
	}

	@Test
	void testDeleteDriverByIdWhenDriverExists() {
		int driverId = 1;

		when(driverRepository.findById(driverId)).thenReturn(Optional.of(new Driver()));

		driverService.deleteDriverById(driverId);

		verify(driverRepository, times(1)).deleteById(driverId);
	}

	@Test
	void testDeleteDriverByIdWhenDriverDoesNotExist() {
		int driverId = 1;

		when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

		assertThrows(NoDriverFoundException.class, () -> {
			driverService.deleteDriverById(driverId);
		});

		verify(driverRepository, times(0)).deleteById(driverId);
	}
}
