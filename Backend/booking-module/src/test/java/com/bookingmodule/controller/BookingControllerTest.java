package com.bookingmodule.controller;

import com.bookingmodule.entity.Booking;
import com.bookingmodule.serviceImpl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingServiceImpl bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.getAllBookings()).thenReturn(bookings);

        ResponseEntity<?> response = bookingController.getAllBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());

        verify(bookingService, times(1)).getAllBookings();
        verifyNoMoreInteractions(bookingService);
    }

    @Test
    public void testGetBookingById_ExistingId() {
        int bookingId = 1;
        Booking booking = new Booking();

        when(bookingService.getBookingById(bookingId)).thenReturn(booking);

        ResponseEntity<?> response = bookingController.getBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(booking, response.getBody());

        verify(bookingService, times(1)).getBookingById(bookingId);
        verifyNoMoreInteractions(bookingService);
    }

   

    @Test
    public void testAddBooking_ValidBooking() {
        Booking booking = new Booking();

        ResponseEntity<?> response = bookingController.addBooking(booking);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("You have successfully booked", response.getBody());

        verify(bookingService, times(1)).addBooking(booking);
        verifyNoMoreInteractions(bookingService);
    }

   

    @Test
    public void testDeleteBookingById_ExistingId() {
        int bookingId = 1;

        ResponseEntity<?> response = bookingController.deleteBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking Canceled Successfully", response.getBody());

        verify(bookingService, times(1)).deleteBookingById(bookingId);
        verifyNoMoreInteractions(bookingService);
    }

   

    @Test
    public void testUpdateBookingById_ExistingId_ValidBooking() {
        int bookingId = 1;
        Booking booking = new Booking();

        ResponseEntity<?> response = bookingController.updateBookingById(booking, bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking Data Updated Successfully!!", response.getBody());

        verify(bookingService, times(1)).updateBookingById(booking, bookingId);
        verifyNoMoreInteractions(bookingService);
    }

 

   
    @Test
    public void testViewAllBookingByCustomerId_ExistingId() {
        int customerId = 1;
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.viewAllBookingByCustomerId(customerId)).thenReturn(bookings);

        ResponseEntity<?> response = bookingController.viewAllBookingByCustomerId(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());

        verify(bookingService, times(1)).viewAllBookingByCustomerId(customerId);
        verifyNoMoreInteractions(bookingService);
    }

   

    @Test
    public void testViewAllBookingByDate_ValidDate() {
        LocalDate date = LocalDate.now();
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.viewAllBookingByDate(date)).thenReturn(bookings);

        ResponseEntity<?> response = bookingController.viewAllBookingByDate(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookings, response.getBody());

        verify(bookingService, times(1)).viewAllBookingByDate(date);
        verifyNoMoreInteractions(bookingService);
    }

   

}

