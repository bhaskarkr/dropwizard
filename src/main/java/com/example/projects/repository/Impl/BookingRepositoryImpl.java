package com.example.projects.repository.Impl;

import com.example.projects.repository.BookingRepository;
import com.example.projects.storage.StoredBooking;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.sharding.dao.RelationalDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.example.projects.constants.BaseConstants.SHARDING_KEY;

@Singleton
public class BookingRepositoryImpl implements BookingRepository {

    private final RelationalDao<StoredBooking> accessor;

    @Inject
    public BookingRepositoryImpl(RelationalDao<StoredBooking> accessor) {
        this.accessor = accessor;
    }

    @Override
    public StoredBooking save(StoredBooking booking) throws Exception {
        return accessor.save(SHARDING_KEY, booking).get();
    }

    @Override
    public Optional<StoredBooking> getById(String bookingId, boolean allowInactive) throws Exception{
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredBooking.class);
        detachedCriteria.add(Restrictions.eq("id", bookingId));
        if(!allowInactive){
            detachedCriteria.add(Restrictions.eq("active", true));
        }
        return accessor.select(SHARDING_KEY, detachedCriteria).stream().findFirst();
    }

    @Override
    public boolean update(String bookingId, UnaryOperator<StoredBooking> unaryOperator){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoredBooking.class);
        detachedCriteria.add(Restrictions.eq("id", bookingId));
        return accessor.update(SHARDING_KEY, detachedCriteria, unaryOperator);
    }
}
