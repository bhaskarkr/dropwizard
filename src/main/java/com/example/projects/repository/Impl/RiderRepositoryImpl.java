package com.example.projects.repository.Impl;

import com.example.projects.repository.RiderRepository;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.example.projects.constant.CabConstants.SHARDING_KEY;

@Singleton
public class RiderRepositoryImpl implements RiderRepository {

    private final RelationalDao<StoredRider> relationalDao;

    @Inject
    public RiderRepositoryImpl(RelationalDao<StoredRider> relationalDao) {
        this.relationalDao = relationalDao;
    }

    @Override
    public Optional<StoredRider> get(String id, boolean allowInactive) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredRider.class);
        detachedCriteria.add(Restrictions.eq("id", id));
        if(!allowInactive)
            detachedCriteria.add(Restrictions.eq("active", true));
        return relationalDao.select(SHARDING_KEY, detachedCriteria).stream().findFirst();
    }

    @Override
    public Optional<StoredRider> save(StoredRider storedRider) throws Exception {
        return relationalDao.save(SHARDING_KEY, storedRider);
    }

    @Override
    public boolean update(String id, UnaryOperator<StoredRider> storedRiderUnaryOperator) {
        return relationalDao.update(SHARDING_KEY, id, storedRiderUnaryOperator);
    }

    @Override
    public Optional<StoredRider> getByPhoneNumber(String phoneNumber) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredRider.class);
        if(phoneNumber != null)
            detachedCriteria.add(Restrictions.eq("phoneNumber", phoneNumber));
        return relationalDao.scatterGather(detachedCriteria).stream().findFirst();
    }

}
