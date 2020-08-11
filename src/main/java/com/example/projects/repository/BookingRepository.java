package com.example.projects.repository;

import com.example.projects.model.Booking;
import com.example.projects.storage.StoredBooking;

import java.util.function.UnaryOperator;

public interface BookingRepository {

    StoredBooking save(StoredBooking booking) throws Exception;

    StoredBooking getById(String bookingid, boolean allowInactive);

    boolean update(String bookingId, UnaryOperator<StoredBooking> unaryOperator) throws Exception;

}
