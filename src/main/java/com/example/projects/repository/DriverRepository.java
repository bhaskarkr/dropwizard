package com.example.projects.repository;


import com.example.projects.enums.DriverStatus;
import com.example.projects.storage.StoredDriver;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public interface DriverRepository {
    Optional<StoredDriver> get(String id, boolean allowInactive, DriverStatus status) throws Exception;
    Optional<StoredDriver> getByPhoneNumber(String phoneNumber) throws Exception;
    Optional<StoredDriver> save(StoredDriver storedDriver) throws Exception;
    boolean update(String id, UnaryOperator<StoredDriver> driverUnaryOperator) throws Exception;
    List<StoredDriver> getNearest(Double lat, Double lng) throws Exception;
}
