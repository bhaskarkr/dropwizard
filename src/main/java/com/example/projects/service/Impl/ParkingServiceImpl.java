package com.example.projects.service.Impl;

import com.example.projects.core.BaseException;
import com.example.projects.core.ErrorCode;
import com.example.projects.model.ParkingLot;
import com.example.projects.model.request.OnboardParkingLotRequest;
import com.example.projects.repository.ParkingLotRepository;
import com.example.projects.service.ParkingService;
import com.example.projects.storage.StoredParkingLot;
import com.example.projects.util.ParkingUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class ParkingServiceImpl implements ParkingService {

    private final ParkingLotRepository parkingLotRepository;

    @Inject
    public ParkingServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot onboardParkingLot(OnboardParkingLotRequest onboardParkingLotRequest) throws Exception{
        return ParkingUtils.toDto(parkingLotRepository.addParkingLot(ParkingUtils.toDao(onboardParkingLotRequest)));
    }

    @Override
    public ParkingLot getParkingLot(String parkingId, boolean allowInactive) throws Exception {
        Optional<StoredParkingLot> storedParkingLot = parkingLotRepository.getbyId(parkingId, allowInactive);
        if(!storedParkingLot.isPresent()){
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Parking lot id not found");
        }
        return ParkingUtils.toDto(storedParkingLot.get());
    }
}
