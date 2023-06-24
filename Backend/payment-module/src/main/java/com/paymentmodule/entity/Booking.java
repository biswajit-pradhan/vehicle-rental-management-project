package com.paymentmodule.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	@OneToOne
	private Customer customer;
	@OneToOne
	private Vehicle vehicle;
	private LocalDate bookingDate;
	private LocalDate bookedTillDate;
	private String bookingDescription;
	private double totalCost;
	private double distance;

}
