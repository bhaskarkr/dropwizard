package com.example.projects.service;

import com.example.projects.model.Booking;
import com.example.projects.model.Driver;
import com.example.projects.enums.DriverStatus;
import com.example.projects.model.Rider;
import com.example.projects.model.request.CreateBookingRequest;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;

import java.util.List;

public interface CabService {

    Driver addDriver(CreateDriverRequest request) throws Exception;

    Driver getDriver(String id, boolean allowInactive, DriverStatus status) throws Exception;

    boolean updateDriverLocation(String id, Double lat, Double lng) throws Exception;

    boolean updateDriverStatus(String id, DriverStatus status) throws Exception;

    Rider addRider(CreateRiderRequest request) throws Exception;

    Rider getRider(String id, boolean allowInactive) throws Exception;

    List<Driver> getNearest(Double lat, Double lng) throws Exception;

    Booking addBooking(CreateBookingRequest bookingRequest) throws Exception;

    List<Booking> getBookingsForRider(String riderId) throws Exception;

    List<Booking> getBookingsForDriver(String driverId) throws Exception;
}
