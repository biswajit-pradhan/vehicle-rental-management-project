package com.vehiclemodule.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vehiclemodule.entity.Driver;
import com.vehiclemodule.serviceImpl.DriverServiceImpl;

class DriverControllerTest {

    @Mock
    private DriverServiceImpl driverService;

    @InjectMocks
    private DriverController driverController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());

        when(driverService.getAllDrivers()).thenReturn(drivers);

        ResponseEntity<?> response = driverController.getAllDrivers();

        verify(driverService, times(1)).getAllDrivers();
        assertSame(drivers, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetDriverById() {
        int driverId = 1;
        Driver driver = new Driver();
        driver.setDriverId(driverId);

        when(driverService.getDriverById(driverId)).thenReturn(driver);

        ResponseEntity<?> response = driverController.getDriverById(driverId);

        verify(driverService, times(1)).getDriverById(driverId);
        assertSame(driver, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAddDriver() {
        Driver driver = new Driver();

        ResponseEntity<?> response = driverController.addDriver(driver);

        verify(driverService, times(1)).addDriver(driver);
        assertEquals("Driver details added successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteDriverById() {
        int driverId = 1;

        ResponseEntity<?> response = driverController.deleteDriverById(driverId);

        verify(driverService, times(1)).deleteDriverById(driverId);
        assertEquals("Driver Details Deleted Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateDriverById() {
        int driverId = 1;
        Driver driver = new Driver();

        ResponseEntity<?> response = driverController.updateDriverById(driver, driverId);

        verify(driverService, times(1)).updateDriverById(driverId, driver);
        assertEquals("Driver Data Updated Successfully", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
