package com.example.projects;

import com.example.projects.model.BillingConfig;
import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.BookingRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.repository.Impl.BookingRepositoryImpl;
import com.example.projects.repository.Impl.ParkingLotRepositoryImpl;
import com.example.projects.repository.ParkingLotRepository;
import com.example.projects.service.BaseService;
import com.example.projects.service.BookingService;
import com.example.projects.service.Impl.BaseServiceImpl;
import com.example.projects.service.Impl.BookingServiceImpl;
import com.example.projects.service.Impl.ParkingServiceImpl;
import com.example.projects.service.ParkingService;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredBooking;
import com.example.projects.storage.StoredParkingLot;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.sharding.DBShardingBundle;
import io.dropwizard.sharding.dao.RelationalDao;

import static io.dropwizard.sharding.DBShardingBundle.createRelatedObjectDao;

public class BaseModule extends AbstractModule {

    DBShardingBundle<BaseProjectConfiguration> dbShardingBundle;

    public BaseModule(DBShardingBundle<BaseProjectConfiguration> dbShardingBundle) {
        this.dbShardingBundle = dbShardingBundle;
    }

    @Override
    protected void configure() {
        bind(BaseService.class).to(BaseServiceImpl.class);
        bind(BaseRepository.class).to(BaseRepositoryImpl.class);
        bind(ParkingLotRepository.class).to(ParkingLotRepositoryImpl.class);
        bind(ParkingService.class).to(ParkingServiceImpl.class);
        bind(BookingService.class).to(BookingServiceImpl.class);
        bind(BookingRepository.class).to(BookingRepositoryImpl.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredBase> baseRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredBase.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredParkingLot> parkingLotRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredParkingLot.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredBooking> bookingRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredBooking.class);
    }

    @Provides
    @Singleton
    public BillingConfig getBillingConfig(BaseProjectConfiguration baseProjectConfiguration) {
        return baseProjectConfiguration.getBillingConfig();
    }
}
