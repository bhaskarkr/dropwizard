package com.example.projects.repository.Impl;

import com.example.projects.repository.BookingRepository;
import com.example.projects.storage.StoredBooking;
import com.google.inject.Singleton;

import java.util.function.UnaryOperator;

@Singleton
public class BookingRepositoryImpl implements BookingRepository {
    @Override
    public StoredBooking save(StoredBooking booking) throws Exception {
        return null;
    }

    @Override
    public StoredBooking getById(String bookingid, boolean allowInactive) {
        return null;
    }

    @Override
    public boolean update(String bookingId, UnaryOperator<StoredBooking> unaryOperator) throws Exception {
        return false;
    }
}
