package com.example.projects.repository;

import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredRides;

import java.util.Optional;

public interface RidesRepository {
    Optional<StoredRides> get(String id, boolean allowInactive) throws Exception;
    Optional<StoredRides> save(StoredBase storedBase) throws Exception;
}
