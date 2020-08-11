package com.example.projects.service.Impl;

import com.example.projects.BaseTest;
import com.example.projects.enums.VehicleType;
import com.example.projects.model.Booking;
import com.example.projects.model.ParkingLot;
import com.example.projects.util.MockGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookingServiceImplTest extends BaseTest {

    @BeforeClass
    public static void setUp() throws Exception {
        bookingService = new BookingServiceImpl(bookingRepository);
        parkingService = new ParkingServiceImpl(parkingLotRepository);
    }

    @Test
    public void registerVehicleEntry() throws Exception{
        ParkingLot parking = parkingService.onboardParkingLot(MockGenerator.getParkingLotOnboardingRequest());
        Booking booking = bookingService.registerBooking(MockGenerator.getBookingRequest(parking.getId(), VehicleType.TWO_WHEELER));
        Assert.assertTrue(booking.getParkingLotId().equals(parking.getId()));
    }

    @Test
    public void finishBooking() throws Exception{
        ParkingLot parking = parkingService.onboardParkingLot(MockGenerator.getParkingLotOnboardingRequest());
        Booking booking = bookingService.registerBooking(MockGenerator.getBookingRequest(parking.getId(), VehicleType.TWO_WHEELER));
        int cost = bookingService.finishBooking(booking.getBookingId());
        Assert.assertTrue(cost > 0);
    }
}