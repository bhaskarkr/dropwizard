package com.example.projects.repository.Impl;

import com.example.projects.repository.BaseRepository;
import com.example.projects.storage.StoredBase;
import com.google.inject.Singleton;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class BaseRepositoryImpl implements BaseRepository {

    private final ConcurrentHashMap<String, StoredBase> store = new ConcurrentHashMap<>();

    @Override
    public Optional<StoredBase> get(String id, boolean allowInactive) throws Exception {
        StoredBase stored = store.get(id);
        if (stored == null) {
            return Optional.empty();
        }
        if (!allowInactive && !stored.isActive()) {
            return Optional.empty();
        }
        return Optional.of(stored);
    }

    @Override
    public Optional<StoredBase> save(StoredBase storedBase) throws Exception {
        Date now = new Date();
        if (store.containsKey(storedBase.getId())) {
            storedBase.setUpdatedAt(now);
        } else {
            storedBase.setCreatedAt(now);
            storedBase.setUpdatedAt(now);
        }
        store.put(storedBase.getId(), storedBase);
        return Optional.of(storedBase);
    }
}
