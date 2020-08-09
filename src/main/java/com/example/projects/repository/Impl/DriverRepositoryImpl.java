package com.example.projects.repository.Impl;

import com.example.projects.model.DriverStatus;
import com.example.projects.repository.DriverRepository;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredDriver;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;
import java.util.function.UnaryOperator;

@Singleton
public class DriverRepositoryImpl implements DriverRepository {
    private final RelationalDao<StoredDriver> relationalDao;

    @Inject
    public DriverRepositoryImpl(RelationalDao<StoredDriver> relationalDao) {
        this.relationalDao = relationalDao;
    }

    @Override
    public Optional<StoredDriver> get(String id, boolean allowInactive, DriverStatus status) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredDriver.class);
        detachedCriteria.add(Restrictions.eq("id", id));
        if(!allowInactive)
            detachedCriteria.add(Restrictions.eq("active", true));
        if(status != null)
            detachedCriteria.add(Restrictions.eq("status", status));
        return relationalDao.select(id, detachedCriteria).stream().findFirst();
    }

    @Override
    public Optional<StoredDriver> getByPhoneNumber(String phoneNumber) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredDriver.class);
        if(phoneNumber != null)
            detachedCriteria.add(Restrictions.eq("phoneNumber", phoneNumber));
        return relationalDao.scatterGather(detachedCriteria).stream().findFirst();
    }

    @Override
    public Optional<StoredDriver> save(StoredDriver storedDriver) throws Exception {
        return relationalDao.save(storedDriver.getId(), storedDriver);
    }

    @Override
    public boolean update(String id, UnaryOperator<StoredDriver> driverUnaryOperator) {
        return relationalDao.update(id, id, driverUnaryOperator);
    }

}
