package com.example.projects.service.Impl;

import com.example.projects.core.BaseException;
import com.example.projects.core.ErrorCode;
import com.example.projects.model.DriverStatus;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.repository.DriverRepository;
import com.example.projects.repository.RiderRepository;
import com.example.projects.repository.RidesRepository;
import com.example.projects.service.CabService;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.util.CabUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class CabServiceImpl implements CabService {
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final RidesRepository ridesRepository;

    @Inject
    public CabServiceImpl(DriverRepository driverRepository,
                          RiderRepository riderRepository,
                          RidesRepository ridesRepository) {
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.ridesRepository = ridesRepository;
    }

    @Override
    public StoredDriver addDriver(CreateDriverRequest request) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.getByPhoneNumber(request.getPhoneNumber());
        if(storedDriver.isPresent())
            throw BaseException.error(ErrorCode.PHONE_NUMBER_ALREADY_EXIST, "Phone Number already Exists");
        return driverRepository.save(CabUtils.toDao(request)).get();
    }

    @Override
    public StoredDriver getDriver(String id, boolean allowInactive, DriverStatus status) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.get(id, allowInactive, status);
        if(!storedDriver.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "Driver Doesn't Exists");
        return storedDriver.get();
    }

    @Override
    public boolean updateDriverLocation(String id, Double lat, Double lng) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.get(id, true, null);
        if(!storedDriver.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Driver Doesn't Exists");
        return driverRepository.update(id, storedDriverUnary -> {
            storedDriverUnary.setLat(lat);
            storedDriverUnary.setLng(lng);
            return storedDriverUnary;
        });
    }

    @Override
    public boolean updateDriverStatus(String id, DriverStatus status) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.get(id, true, null);
        if(!storedDriver.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Driver Doesn't Exists");
        return driverRepository.update(id, storedDriverUnary -> {
            storedDriverUnary.setStatus(status);
            return storedDriverUnary;
        });
    }

    @Override
    public StoredRider addRider(CreateRiderRequest request) throws Exception {
        Optional<StoredRider> storedRider = riderRepository.getByPhoneNumber(request.getPhoneNumber());
        if(storedRider.isPresent())
            throw BaseException.error(ErrorCode.PHONE_NUMBER_ALREADY_EXIST, "Phone Number already Exists");
        return riderRepository.save(CabUtils.toDao(request)).get();
    }

    @Override
    public StoredRider getRider(String id, boolean allowInactive) throws Exception {
        Optional<StoredRider> storedRider = riderRepository.get(id, allowInactive);
        if(!storedRider.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "Driver Doesn't Exists");
        return storedRider.get();
    }


}
