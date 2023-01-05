package com.example.projects;

import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.service.BaseService;
import com.example.projects.service.Impl.BaseServiceImpl;
import com.example.projects.storage.StoredBase;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appform.dropwizard.sharding.DBShardingBundle;
import io.appform.dropwizard.sharding.dao.RelationalDao;

public class BaseModule extends AbstractModule {

    DBShardingBundle<BaseProjectConfiguration> dbShardingBundle;

    public BaseModule(DBShardingBundle<BaseProjectConfiguration> dbShardingBundle) {
        this.dbShardingBundle = dbShardingBundle;
    }

    @Override
    protected void configure() {
        bind(BaseService.class).to(BaseServiceImpl.class);
        bind(BaseRepository.class).to(BaseRepositoryImpl.class);
    }

    @Provides
    @Singleton
    public RelationalDao<StoredBase> baseRelationalDao() {
        return dbShardingBundle.createRelatedObjectDao(StoredBase.class);
    }
}
