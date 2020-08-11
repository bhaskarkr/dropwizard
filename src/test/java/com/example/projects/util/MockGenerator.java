package com.example.projects.util;

import com.example.projects.enums.VehicleType;
import com.example.projects.model.request.BookingRequest;
import com.example.projects.model.request.OnboardParkingLotRequest;

import java.util.HashMap;

public interface MockGenerator {

    public static final String TEST_ID = "B123";
    public static final String TEST_NAME = "T NAME";
    public static final String TEST_USN = "T USN";
    public static final String TEST_REGISTRATION_ID = "B123";

    static OnboardParkingLotRequest getParkingLotOnboardingRequest() {
        HashMap<VehicleType, Integer> slotsAvailable = new HashMap<>();
        slotsAvailable.put(VehicleType.TWO_WHEELER, 5);
        slotsAvailable.put(VehicleType.HATCH_BACK, 5);
        slotsAvailable.put(VehicleType.SUV, 5);

        HashMap<VehicleType, Integer> slotsCapacity = new HashMap<>();
        slotsCapacity.put(VehicleType.TWO_WHEELER, 5);
        slotsCapacity.put(VehicleType.HATCH_BACK, 5);
        slotsCapacity.put(VehicleType.SUV, 5);

        OnboardParkingLotRequest onboardParkingLotRequest = OnboardParkingLotRequest.builder()
                .address("Address")
                .slotsAvailable(slotsAvailable)
                .slotsCapacity(slotsCapacity)
                .build();
        return onboardParkingLotRequest;
    }

    static BookingRequest getBookingRequest(String parkingId, VehicleType type) {
        return BookingRequest.builder()
                .parkingLotId(parkingId)
                .registrationNumber(TEST_REGISTRATION_ID)
                .type(type)
                .build();
    }

}
