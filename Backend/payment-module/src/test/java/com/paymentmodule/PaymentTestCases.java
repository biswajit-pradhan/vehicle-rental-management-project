package com.paymentmodule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.paymentmodule.entity.Payment;
import com.paymentmodule.dto.PaymentDto;
import com.paymentmodule.entity.Booking;
import com.paymentmodule.exception.NotFoundException;
import com.paymentmodule.repository.BookingRepository;
import com.paymentmodule.repository.PaymentRepository;
import com.paymentmodule.service.PaymentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTestCases {
	@Mock
	private PaymentRepository paymentRepo;

	@Mock
	private BookingRepository bookingRepo;

	@InjectMocks
	private PaymentServiceImpl paymentService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddPayment() throws NotFoundException {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPaymentId(1);
		Booking booking = new Booking();
		booking.setBookingId(1);
		Mockito.when(paymentRepo.findById(paymentDto.getPaymentId())).thenReturn(Optional.empty());
		Mockito.when(bookingRepo.findById(paymentDto.getBookingId())).thenReturn(Optional.of(booking));
		Mockito.when(paymentRepo.save(Mockito.any(Payment.class))).thenAnswer(i -> i.getArgument(0));
		Payment result = paymentService.addPayment(paymentDto);
		Mockito.verify(paymentRepo).findById(paymentDto.getPaymentId());
		Mockito.verify(bookingRepo).findById(paymentDto.getBookingId());
		Mockito.verify(paymentRepo).save(Mockito.any(Payment.class));
		assertNotNull(result);
		assertEquals(paymentDto.getPaymentId(), result.getPaymentId());
	}

	@Test
	public void testCancelPayment_ValidPaymentAndBooking() throws NotFoundException {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPaymentId(123);
		paymentDto.setBookingId(456);
		Payment existingPayment = new Payment();
		existingPayment.setPaymentId(123);
		Booking existingBooking = new Booking();
		existingBooking.setBookingId(456);
		existingPayment.setBooking(existingBooking);
		when(paymentRepo.findById(paymentDto.getPaymentId())).thenReturn(Optional.of(existingPayment));
		when(bookingRepo.findById(paymentDto.getBookingId())).thenReturn(Optional.of(existingBooking));
		Payment result = paymentService.cancelPayment(paymentDto);
		assertEquals(existingPayment, result);
		verify(paymentRepo, times(1)).findById(paymentDto.getPaymentId());
		verify(bookingRepo, times(1)).findById(paymentDto.getBookingId());
		verify(paymentRepo, times(1)).deleteById(paymentDto.getPaymentId());
		verifyNoMoreInteractions(paymentRepo, bookingRepo);
	}

	@Test
	public void testViewPayment() throws NotFoundException {
		int bookingId = 123;
		Payment expectedPayment = new Payment();
		expectedPayment.setPaymentId(456);
		when(paymentRepo.findById(bookingId)).thenReturn(Optional.of(expectedPayment));
		Payment result = paymentService.viewPayment(bookingId);
		assertEquals(expectedPayment, result);
		verify(paymentRepo, times(1)).findById(bookingId);
		verifyNoMoreInteractions(paymentRepo);
	}

	@Test
	public void testViewAllPayment() {
		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		List<Payment> expectedPayments = Arrays.asList(payment1, payment2);
		when(paymentRepo.findAll()).thenReturn(expectedPayments);
		List<Payment> result = paymentService.viewAllPayment();
		assertEquals(expectedPayments, result);
		verify(paymentRepo, times(1)).findAll();
		verifyNoMoreInteractions(paymentRepo);
	}
}
