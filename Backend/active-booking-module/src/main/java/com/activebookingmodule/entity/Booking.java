package com.activebookingmodule.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;

	@NotNull(message = "Customer is required")
	@OneToOne
	private Customer customer;

	@NotNull(message = "Vehicle is required")
	@OneToOne
	private Vehicle vehicle;

	@NotNull(message = "Booking date is required")
	private LocalDate bookingDate;

	@NotNull(message = "Booking till date is required")
	private LocalDate bookingTillDate;

	@NotBlank(message = "Booking description is required")
	private String bookingDescription;

	@Positive(message = "Total cost must be a positive value")
	private double totalCost;

	@Positive(message = "Distance must be a positive value")
	private double distance;
}
