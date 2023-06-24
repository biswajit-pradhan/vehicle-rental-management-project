package com.vehiclemodule.serviceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vehiclemodule.entity.Vehicle;
import com.vehiclemodule.repository.DriverRepository;
import com.vehiclemodule.repository.VehicleRepository;

class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicleService.addVehicle(vehicle);

        verify(driverRepository, times(1)).save(any());
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testDeleteVehicleById() {
        int vehicleId = 1;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(new Vehicle()));

        vehicleService.deleteVehicleById(vehicleId);

        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }

    @Test
    void testUpdateVehicleById() {
        int vehicleId = 1;
        Vehicle vehicle = new Vehicle();
        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setVehicleId(vehicleId);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));

        vehicleService.updateVehicleById(vehicleId, vehicle);

        verify(driverRepository, times(1)).save(any());
        verify(vehicleRepository, times(1)).save(existingVehicle);
    }
}
