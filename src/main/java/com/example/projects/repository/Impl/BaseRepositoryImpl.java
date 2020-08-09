package com.example.projects.repository.Impl;

import com.example.projects.model.Base;
import com.example.projects.repository.BaseRepository;
import com.example.projects.storage.StoredBase;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

@Singleton
public class BaseRepositoryImpl implements BaseRepository {
    private final RelationalDao<StoredBase> storedBaseRelationalDao;

    @Inject
    public BaseRepositoryImpl(RelationalDao<StoredBase> storedBaseRelationalDao) {
        this.storedBaseRelationalDao = storedBaseRelationalDao;
    }

    @Override
    public Optional<StoredBase> get(String id, boolean allowInactive) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredBase.class);
        detachedCriteria.add(Restrictions.eq("id", id));
        if(!allowInactive){
            detachedCriteria.add(Restrictions.eq("active", true));
        }
        return storedBaseRelationalDao.select(id, detachedCriteria, 0, 1).stream().findFirst();
    }

    @Override
    public Optional<StoredBase> save(StoredBase storedBase) throws Exception {
        return storedBaseRelationalDao.save(storedBase.getId(), storedBase);
    }
}
