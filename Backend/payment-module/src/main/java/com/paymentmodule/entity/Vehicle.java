package com.paymentmodule.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicleId;
	@NotNull
	private String vehicleNumber;
	@OneToOne
	private Driver driver;
	@NotNull
	private String type;
	@NotNull
	private String category;
	@NotNull
	private String description;
	@NotNull
	private String location;
	@NotNull
	private int capacity;
	@NotNull
	private double chargesPerKm;
	@NotNull
	private double fixedCharges;
	

}
