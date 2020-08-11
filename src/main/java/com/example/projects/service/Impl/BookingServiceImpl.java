package com.example.projects.service.Impl;

import com.example.projects.model.Booking;
import com.example.projects.model.request.BookingRequest;
import com.example.projects.repository.BookingRepository;
import com.example.projects.service.BookingService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Inject
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    public Booking registerBooking(BookingRequest bookingRequest) throws Exception {
        return null;
    }

    @Override
    public Booking getById(String bookingId, boolean allowInactive) throws Exception {
        return null;
    }

    @Override
    public int finishBooking(String bookingId) throws Exception {
        return 100;
    }
}
