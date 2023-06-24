package com.paymentmodule.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

import com.paymentmodule.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	@NotNull
	private int paymentId;
	@NotNull
	private String paymentMode;
	@NotNull
	private LocalDate paymentDate;
	@NotNull
	private String paymentStatus;
	private int bookingId;
 
}
