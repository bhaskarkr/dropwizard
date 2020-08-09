package com.example.projects.repository;

import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredRider;

import java.util.Optional;
import java.util.function.UnaryOperator;

public interface RiderRepository {

    Optional<StoredRider> get(String id, boolean allowInactive) throws Exception;
    Optional<StoredRider> getByPhoneNumber(String phoneNumber) throws Exception;
    Optional<StoredRider> save(StoredRider storedRider) throws Exception;
    boolean update(String id, UnaryOperator<StoredRider> storedRiderUnaryOperator) throws Exception;
}

