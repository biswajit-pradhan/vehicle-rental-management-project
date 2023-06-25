package com.paymentmodule.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paymentmodule.entity.Booking;
import com.paymentmodule.entity.Payment;
import com.paymentmodule.entity.Vehicle;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query("SELECT COALESCE(SUM(b.totalCost), 0) FROM Booking b WHERE b.bookingDate BETWEEN :startDate AND :endDate")
	double calculateMonthRevenue(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT SUM(b.totalCost) FROM Booking b WHERE b.vehicle.id = :vehicleId")
	Double calculateTotalPaymentByVehicleId(@Param("vehicleId") int vehicleId);

}
