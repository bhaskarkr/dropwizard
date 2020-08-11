package com.example.projects.repository;

import com.example.projects.storage.StoredParkingLot;

import java.util.Optional;

public interface ParkingLotRepository {

    StoredParkingLot addParkingLot(StoredParkingLot storedParkingLot) throws Exception;

    Optional<StoredParkingLot> getbyId(String parkingId, boolean allowInactive) throws Exception;

}
