package com.example.projects.repository.Impl;

import com.example.projects.enums.DriverStatus;
import com.example.projects.repository.DriverRepository;
import com.example.projects.storage.StoredDriver;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.example.projects.constant.CabConstants.SHARDING_KEY;

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
        return relationalDao.select(SHARDING_KEY, detachedCriteria).stream().findFirst();
    }

    @Override
    public Optional<StoredDriver> getByPhoneNumber(String phoneNumber) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredDriver.class);
        if(phoneNumber != null)
            detachedCriteria.add(Restrictions.eq("phoneNumber", phoneNumber));
        return relationalDao.select(SHARDING_KEY, detachedCriteria).stream().findFirst();
    }

    @Override
    public Optional<StoredDriver> save(StoredDriver storedDriver) throws Exception {
        return relationalDao.save(SHARDING_KEY, storedDriver);
    }

    @Override
    public boolean update(String id, UnaryOperator<StoredDriver> driverUnaryOperator) {
        return relationalDao.update(SHARDING_KEY, id, driverUnaryOperator);
    }

    @Override
    public List<StoredDriver> getNearest(Double lat, Double lng) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredDriver.class)
                .add(Restrictions.lt("lat", lat+10))
                .add(Restrictions.gt("lat", lat-10))
                .add(Restrictions.lt("lng", lng+10))
                .add(Restrictions.gt("lng", lng-10))
                .add(Restrictions.eq("status", DriverStatus.IDLE));
        return relationalDao.select(SHARDING_KEY, detachedCriteria, 0 , 100);
    }

}
