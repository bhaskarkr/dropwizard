package com.example.projects.service;

import com.example.projects.model.DriverStatus;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;

public interface CabService {

    StoredDriver addDriver(CreateDriverRequest request) throws Exception;

    StoredDriver getDriver(String id, boolean allowInactive, DriverStatus status) throws Exception;

    boolean updateDriverLocation(String id, Double lat, Double lng) throws Exception;

    boolean updateDriverStatus(String id, DriverStatus status) throws Exception;

    StoredRider addRider(CreateRiderRequest request) throws Exception;

    StoredRider getRider(String id, boolean allowInactive) throws Exception;

}
