package com.example.projects.service;

import com.example.projects.model.ParkingLot;
import com.example.projects.model.request.OnboardParkingLotRequest;

public interface ParkingService {

    ParkingLot onboardParkingLot(OnboardParkingLotRequest onboardParkingLotRequest) throws Exception;

    ParkingLot getParkingLot(String parkingId, boolean allowInactive) throws Exception;

}
