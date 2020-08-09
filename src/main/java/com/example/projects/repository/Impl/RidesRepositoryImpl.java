package com.example.projects.repository.Impl;

import com.example.projects.repository.RidesRepository;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.storage.StoredRides;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

import static com.example.projects.constant.CabConstants.SHARDING_KEY;

@Singleton
public class RidesRepositoryImpl implements RidesRepository {

    private final RelationalDao<StoredRides> relationalDao;

    @Inject
    public RidesRepositoryImpl(RelationalDao<StoredRides> relationalDao) {
        this.relationalDao = relationalDao;
    }

    @Override
    public List<StoredRides> get(String id, StoredRider rider, StoredDriver driver) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredRides.class);
        if(id != null)
            detachedCriteria.add(Restrictions.eq("id", id));
        if(rider != null)
            detachedCriteria.add(Restrictions.eq("rider", rider));
        if(driver != null)
            detachedCriteria.add(Restrictions.eq("driver", driver));
        return relationalDao.scatterGather(detachedCriteria);
    }

    @Override
    public Optional<StoredRides> save(StoredRides storedRides) throws Exception {
        return relationalDao.save(SHARDING_KEY, storedRides);
    }

}
