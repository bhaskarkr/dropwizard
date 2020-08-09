package com.example.projects.repository;

import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.storage.StoredRides;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public interface RidesRepository {
    List<StoredRides> get(String id, StoredRider rider, StoredDriver driver) throws Exception;
    Optional<StoredRides> save(StoredRides storedRides) throws Exception;
    boolean update(String id, UnaryOperator<StoredRides> storedRidesUnaryOperator) throws Exception;
}
