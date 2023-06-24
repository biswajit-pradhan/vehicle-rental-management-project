package com.bookingmodule.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int driverId;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Mobile number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be a 10-digit number")
    private String mobileNumber;

    @NotEmpty(message = "Email ID cannot be empty")
    @Email(message = "Invalid email format")
    private String emailId;

    @NotEmpty(message = "License number cannot be empty")
    @Size(min = 6, max = 20, message = "License number must be between 6 and 20 characters")
    private String licenceNo;
}

