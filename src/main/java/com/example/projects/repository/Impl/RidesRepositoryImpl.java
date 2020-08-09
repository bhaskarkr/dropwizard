package com.example.projects.repository.Impl;

import com.example.projects.repository.RidesRepository;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredRides;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;

import java.util.Optional;

@Singleton
public class RidesRepositoryImpl implements RidesRepository {

    private final RelationalDao<StoredRides> relationalDao;

    @Inject
    public RidesRepositoryImpl(RelationalDao<StoredRides> relationalDao) {
        this.relationalDao = relationalDao;
    }

    @Override
    public Optional<StoredRides> get(String id, boolean allowInactive) throws Exception {
        return Optional.empty();
    }

    @Override
    public Optional<StoredRides> save(StoredBase storedBase) throws Exception {
        return Optional.empty();
    }
}
