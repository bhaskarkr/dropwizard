package com.example.projects.repository;

import com.example.projects.model.Base;
import com.example.projects.storage.StoredBase;

import java.util.Optional;

public interface BaseRepository {

    Optional<StoredBase> get(String id, boolean allowInactive) throws Exception;

    Optional<StoredBase> save(StoredBase storedBase) throws Exception;

}
