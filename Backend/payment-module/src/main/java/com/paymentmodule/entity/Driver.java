package com.paymentmodule.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int driverId;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String address;
	@NotNull
	private String mobileNumber;
	@NotNull
	private String emailId;
	@NotNull
	private String licenseNo;
	

}
