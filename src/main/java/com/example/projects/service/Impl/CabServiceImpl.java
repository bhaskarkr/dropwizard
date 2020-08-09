package com.example.projects.service.Impl;

import com.example.projects.core.BaseException;
import com.example.projects.core.ErrorCode;
import com.example.projects.enums.RiderStatus;
import com.example.projects.model.Booking;
import com.example.projects.model.Driver;
import com.example.projects.enums.DriverStatus;
import com.example.projects.model.Rider;
import com.example.projects.model.request.CreateBookingRequest;
import com.example.projects.model.request.CreateDriverRequest;
import com.example.projects.model.request.CreateRiderRequest;
import com.example.projects.repository.DriverRepository;
import com.example.projects.repository.RiderRepository;
import com.example.projects.repository.RidesRepository;
import com.example.projects.service.CabService;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.storage.StoredRides;
import com.example.projects.util.CabUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Driver addDriver(CreateDriverRequest request) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.getByPhoneNumber(request.getPhoneNumber());
        if(storedDriver.isPresent())
            throw BaseException.error(ErrorCode.PHONE_NUMBER_ALREADY_EXIST, "Phone Number already Exists");
        return CabUtils.toDto(driverRepository.save(CabUtils.toDao(request)).get());
    }

    @Override
    public Driver getDriver(String id, boolean allowInactive, DriverStatus status) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.get(id, allowInactive, status);
        if(!storedDriver.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "Driver Doesn't Exists");
        return CabUtils.toDto(storedDriver.get());
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
    public Rider addRider(CreateRiderRequest request) throws Exception {
        Optional<StoredRider> storedRider = riderRepository.getByPhoneNumber(request.getPhoneNumber());
        if(storedRider.isPresent())
            throw BaseException.error(ErrorCode.PHONE_NUMBER_ALREADY_EXIST, "Phone Number already Exists");
        return CabUtils.toDto(riderRepository.save(CabUtils.toDao(request)).get());
    }

    @Override
    public Rider getRider(String id, boolean allowInactive) throws Exception {
        Optional<StoredRider> storedRider = riderRepository.get(id, allowInactive);
        if(!storedRider.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "User Doesn't Exists");
        return CabUtils.toDto(storedRider.get());
    }

    @Override
    public List<Driver> getNearest(Double lat, Double lng) throws Exception {
        return driverRepository.getNearest(lat, lng).stream().map(CabUtils::toDto).collect(Collectors.toList());
    }

    @Override
    public Booking addBooking(CreateBookingRequest bookingRequest) throws Exception {
        Optional<StoredRider> rider = riderRepository.get(bookingRequest.getRiderId(), false);
        if(!rider.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "User Doesn't Exists");
        if(rider.get().getStatus() == RiderStatus.ONGOING)
            throw BaseException.error(ErrorCode.TRIP_ALREADY_INPROGRESS, "User's trip already in progress");
        Optional<StoredDriver> driver = driverRepository.get(bookingRequest.getDriverId(), false, DriverStatus.IDLE);
        if(!driver.isPresent())
            throw BaseException.error(ErrorCode.INVALID_REQUEST, "Driver Doesn't Exists");
        return CabUtils.toDto(ridesRepository.save(CabUtils.toDao(bookingRequest, rider.get(), driver.get())).get());
    }

    @Override
    public List<Booking> getBookingsForRider(String riderId) throws Exception {
        Optional<StoredRider> storedRider = riderRepository.get(riderId, false);
        if(!storedRider.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "User Doesn't Exists");
        return ridesRepository.get(null, storedRider.get(), null).stream().map(CabUtils::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Booking>  getBookingsForDriver(String driverId) throws Exception {
        Optional<StoredDriver> storedDriver = driverRepository.get(driverId, false, null);
        if(!storedDriver.isPresent())
            throw BaseException.error(ErrorCode.NO_RESULT_FOUND, "Driver Doesn't Exists");
        return ridesRepository.get(null, null, storedDriver.get()).stream().map(CabUtils::toDto).collect(Collectors.toList());
    }

    private List<Driver> sortByDistance(List<Driver> drivers, Double lat, Double lng) {
        if(drivers.size() < 10)
            return drivers;
        Collections.sort(drivers, new Comparator<Driver>()
        {
            @Override
            public int compare(Driver o1, Driver o2) {
                double p0 = Math.pow(o1.getLat()-lat, 2) + Math.pow(o1.getLng()-lng, 2);
                double p1 = Math.pow(o2.getLat()-lat, 2) + Math.pow(o2.getLng()-lng, 2);
                return Double.compare(p0, p1);
            }
        });
        return drivers;
    }

}
