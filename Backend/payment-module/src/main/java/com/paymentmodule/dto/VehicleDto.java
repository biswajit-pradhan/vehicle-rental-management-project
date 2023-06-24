package com.paymentmodule.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.paymentmodule.entity.Driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
	@NotNull
	private int vehicleId;
	@NotNull
	private String vehicleNumber;
	private int driverId;
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
