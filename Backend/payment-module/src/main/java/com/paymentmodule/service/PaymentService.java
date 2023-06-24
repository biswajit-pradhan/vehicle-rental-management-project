package com.paymentmodule.service;

import java.time.LocalDate;
import java.util.List;

import com.paymentmodule.dto.PaymentDto;
import com.paymentmodule.dto.VehicleDto;
import com.paymentmodule.entity.Booking;
import com.paymentmodule.entity.Payment;
import com.paymentmodule.entity.Vehicle;
import com.paymentmodule.exception.NotFoundException;

public interface PaymentService  {
	public Payment addPayment(PaymentDto payment)throws NotFoundException;
	public Payment cancelPayment(PaymentDto payment)throws NotFoundException;
	public Payment viewPayment(Payment payment)throws NotFoundException;
	public List<Payment> viewAllPayment();
	public double calculateMonthlyRevenue(LocalDate d1,LocalDate d2);
	public Double calculateTotalPayment(int vehicleId);

}
