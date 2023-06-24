package com.paymentmodule;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
        Assertions.assertNotNull(result);
        Assertions.assertEquals(paymentDto.getPaymentId(), result.getPaymentId());
    }
    
//    @Test
//    public void testCancelPayment() {
//        
//        PaymentDto paymentDto = new PaymentDto();
//        paymentDto.setPaymentId(1);
//        Payment payment = new Payment();
//        payment.setPaymentId(1);
//        Booking booking = new Booking();
//        booking.setBookingId(1);
//        Mockito.when(paymentRepo.findById(paymentDto.getPaymentId())).thenReturn(Optional.of(payment));
//        Mockito.when(bookingRepo.findById(paymentDto.getBookingId())).thenReturn(Optional.of(booking));
//
//        Payment result = null;
//        try {
//            result = paymentService.cancelPayment(paymentDto);
//        } catch (NotFoundException e) {
//        	Assertions.assertEquals("not matching", e.getMessage());
//        }
//        Mockito.verify(paymentRepo).findById(paymentDto.getPaymentId());
//        Mockito.verify(bookingRepo).findById(paymentDto.getBookingId());
//        Assertions.assertEquals(payment.getPaymentId(), result.getPaymentId());
//    }

}
