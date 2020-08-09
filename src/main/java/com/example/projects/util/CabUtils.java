package com.example.projects.util;

import com.example.projects.enums.RideStatus;
import com.example.projects.model.Booking;
import com.example.projects.model.Driver;
import com.example.projects.enums.DriverStatus;
import com.example.projects.model.Rider;
import com.example.projects.enums.RiderStatus;
import com.example.projects.model.request.CreateBookingRequest;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.storage.StoredRides;
import io.appform.dropwizard.discovery.bundle.id.IdGenerator;

public interface CabUtils {

    static StoredDriver toDao(CreateDriverRequest request) {
        return StoredDriver.builder()
                .id(IdGenerator.generate("D").getId())
                .active(true)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .status(DriverStatus.NOT_AVAILABLE)
                .vehicleNumber(request.getVehicleNumber())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();
    }

    static Driver toDto(StoredDriver storedDriver) {
        return Driver.builder()
                .id(storedDriver.getId())
                .active(storedDriver.getActive())
                .lat(storedDriver.getLat())
                .lng(storedDriver.getLng())
                .name(storedDriver.getName())
                .phoneNumber(storedDriver.getPhoneNumber())
                .status(storedDriver.getStatus())
                .vehicleNumber(storedDriver.getVehicleNumber())
                .build();
    }

    static StoredRider toDao(CreateRiderRequest request) {
        return StoredRider.builder()
                .id(IdGenerator.generate("U").getId())
                .active(true)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .status(RiderStatus.IDLE)
                .build();
    }

    static Rider toDto(StoredRider storedRider) {
        return Rider.builder()
                .id(storedRider.getId())
                .active(storedRider.getActive())
                .name(storedRider.getName())
                .phoneNumber(storedRider.getPhoneNumber())
                .status(storedRider.getStatus())
                .build();
    }

    static StoredRides toDao(CreateBookingRequest request, StoredRider rider, StoredDriver driver) {
        return StoredRides.builder()
                .id(IdGenerator.generate("U").getId())
                .driver(driver)
                .rider(rider)
                .status(RideStatus.ACTIVE)
                .pickLat(request.getPickLat())
                .pickLng(request.getPickLng())
                .dropLat(request.getDropLat())
                .dropLng(request.getDropLng())
                .build();
    }

    static Booking toDto(StoredRides storedRides) {
        return Booking.builder()
                .driver(toDto(storedRides.getDriver()))
                .rider(toDto(storedRides.getRider()))
                .pickLat(storedRides.getPickLat())
                .pickLng(storedRides.getPickLng())
                .dropLat(storedRides.getDropLat())
                .dropLng(storedRides.getDropLng())
                .status(storedRides.getStatus())
                .build();
    }

}
