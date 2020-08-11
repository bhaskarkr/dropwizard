package com.example.projects.service;

import com.example.projects.model.Booking;
import com.example.projects.model.request.BookingRequest;

public interface BookingService {

    Booking registerBooking(BookingRequest bookingRequest) throws Exception;

    Booking getById(String bookingId, boolean allowInactive) throws Exception;

    int finishBooking(String bookingId) throws Exception;

}
