package com.example.projects.util;

import com.example.projects.enums.VehicleType;
import com.example.projects.model.BillingConfig;
import com.example.projects.model.request.BookingRequest;
import com.example.projects.model.request.OnboardParkingLotRequest;
import com.google.common.collect.ImmutableList;
import io.swagger.models.auth.In;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    static BillingConfig getBillingConfig() {
        ArrayList<Integer> twoWheeler = new ArrayList<>();
        twoWheeler.add(10);
        twoWheeler.add(8);
        twoWheeler.add(5);
        ArrayList<Integer> hatchBack = new ArrayList<>();
        hatchBack.add(15);
        hatchBack.add(12);
        hatchBack.add(10);
        ArrayList<Integer> suv = new ArrayList<>();
        suv.add(20);
        suv.add(17);
        suv.add(15);
        HashMap<VehicleType, ArrayList<Integer>> rateCard = new HashMap();
        rateCard.put(VehicleType.TWO_WHEELER, twoWheeler);
        rateCard.put(VehicleType.HATCH_BACK, hatchBack);
        rateCard.put(VehicleType.SUV, suv);

        ArrayList<Integer> hourSplit = new ArrayList();
        hourSplit.add(2);
        hourSplit.add(2);
        hourSplit.add(1000);
        return BillingConfig.builder()
                .rateCard(rateCard)
                .hourSplit(hourSplit)
                .build();
    }

}
