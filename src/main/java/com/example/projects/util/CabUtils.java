package com.example.projects.util;

import com.example.projects.model.Driver;
import com.example.projects.model.DriverStatus;
import com.example.projects.model.Rider;
import com.example.projects.model.RiderStatus;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
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

}
