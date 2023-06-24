package com.vehiclemodule.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.vehiclemodule.entity.Vehicle;
import com.vehiclemodule.serviceImpl.VehicleServiceImpl;

class VehicleControllerTest {

    @Mock
    private VehicleServiceImpl vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());

        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        ResponseEntity<?> response = vehicleController.getAllVehicles();

        verify(vehicleService, times(1)).getAllVehicles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicles, response.getBody());
    }

    @Test
    void testGetVehicleById() {
        int vehicleId = 1;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);

        when(vehicleService.getVehicleById(vehicleId)).thenReturn(vehicle);

        ResponseEntity<?> response = vehicleController.getVehicleById(vehicleId);

        verify(vehicleService, times(1)).getVehicleById(vehicleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicle, response.getBody());
    }

    @Test
    void testAddVehicle() {
        Vehicle vehicle = new Vehicle();

        ResponseEntity<?> response = vehicleController.addVehicle(vehicle);

        verify(vehicleService, times(1)).addVehicle(vehicle);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vehicle details added successfully", response.getBody());
    }

    @Test
    void testDeleteVehicleById() {
        int vehicleId = 1;

        ResponseEntity<?> response = vehicleController.deleteVehicleById(vehicleId);

        verify(vehicleService, times(1)).deleteVehicleById(vehicleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vehicle Details Deleted Successfully", response.getBody());
    }

    @Test
    void testUpdateVehicleById() {
        int vehicleId = 1;
        Vehicle vehicle = new Vehicle();

        ResponseEntity<?> response = vehicleController.updateVehicleById(vehicle, vehicleId);

        verify(vehicleService, times(1)).updateVehicleById(vehicleId, vehicle);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vehicle Data Updated Successfully", response.getBody());
    }
}
