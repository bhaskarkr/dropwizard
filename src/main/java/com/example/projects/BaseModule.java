package com.example.projects;

import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.DriverRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.repository.Impl.DriverRepositoryImpl;
import com.example.projects.repository.Impl.RiderRepositoryImpl;
import com.example.projects.repository.Impl.RidesRepositoryImpl;
import com.example.projects.repository.RiderRepository;
import com.example.projects.repository.RidesRepository;
import com.example.projects.service.BaseService;
import com.example.projects.service.CabService;
import com.example.projects.service.Impl.BaseServiceImpl;
import com.example.projects.service.Impl.CabServiceImpl;
import com.example.projects.storage.StoredBase;
import com.example.projects.storage.StoredDriver;
import com.example.projects.storage.StoredRider;
import com.example.projects.storage.StoredRides;
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
        bind(CabService.class).to(CabServiceImpl.class);
        bind(RiderRepository.class).to(RiderRepositoryImpl.class);
        bind(DriverRepository.class).to(DriverRepositoryImpl.class);
        bind(RidesRepository.class).to(RidesRepositoryImpl.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredBase> baseRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredBase.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredDriver> driverRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredDriver.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredRider> riderRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredRider.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredRides> ridesRelationalDao() {
        return createRelatedObjectDao(dbShardingBundle, StoredRides.class);
    }

}
