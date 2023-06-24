package com.bookingmodule.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;

    @NotEmpty(message = "Vehicle number cannot be empty")
    private String vehicleNumber;

    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    @Positive(message = "Charges per kilometer must be a positive number")
    private double chargesPerKM;

    @Positive(message = "Fixed charges must be a positive number")
    private double fixedCharges;

    @NotNull(message = "Driver cannot be null")
    @OneToOne
    private Driver driver;
}
