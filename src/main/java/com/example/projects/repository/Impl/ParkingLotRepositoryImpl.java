package com.example.projects.repository.Impl;

import com.example.projects.repository.ParkingLotRepository;
import com.example.projects.storage.StoredParkingLot;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

import static com.example.projects.constants.BaseConstants.SHARDING_KEY;

@Singleton
public class ParkingLotRepositoryImpl implements ParkingLotRepository {

    private final RelationalDao<StoredParkingLot> accessor;

    @Inject
    public ParkingLotRepositoryImpl(RelationalDao<StoredParkingLot> accessor) {
        this.accessor = accessor;
    }

    @Override
    public StoredParkingLot addParkingLot(StoredParkingLot storedParkingLot) throws Exception {
        return accessor.save(SHARDING_KEY, storedParkingLot).get();
    }

    @Override
    public Optional<StoredParkingLot> getbyId(String parkingId, boolean allowInactive) throws Exception {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredParkingLot.class);
        detachedCriteria.add(Restrictions.eq("id", parkingId));
        if(!allowInactive){
            detachedCriteria.add(Restrictions.eq("active", true));
        }
        return accessor.select(SHARDING_KEY, detachedCriteria, 0, 1).stream().findFirst();
    }
}
