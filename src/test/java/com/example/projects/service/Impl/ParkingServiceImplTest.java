package com.example.projects.service.Impl;

import com.example.projects.BaseTest;
import com.example.projects.core.BaseException;
import com.example.projects.enums.VehicleType;
import com.example.projects.model.ParkingLot;
import com.example.projects.model.request.OnboardParkingLotRequest;
import com.example.projects.util.MockGenerator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParkingServiceImplTest extends BaseTest {

    @BeforeClass
    public static void setUp(){
        parkingService = new ParkingServiceImpl(parkingLotRepository);
    }

    @Test
    public void onboardParkingLot() throws Exception{

        ParkingLot parkingLot = parkingService.onboardParkingLot(MockGenerator.getParkingLotOnboardingRequest());
        assertNotNull("Null object", parkingLot);
        assertTrue(parkingLot.getSlotsAvailable().size() == 3);
        assertTrue(parkingLot.getSlotsCapacity().size() == 3);
        assertTrue(parkingLot.getId() != null);
        ParkingLot parking = parkingService.getParkingLot(parkingLot.getId(), false);
        assertNotNull("get from Db failed", parking);
    }

    @Test(expected = BaseException.class)
    public void getParkingLotByIdFailure() throws Exception{
        ParkingLot parkingLot = parkingService.getParkingLot("123", true);
    }


}