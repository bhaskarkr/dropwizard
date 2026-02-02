package com.example.projects;

import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.service.BaseService;
import com.example.projects.service.Impl.BaseServiceImpl;
import com.google.inject.AbstractModule;

public class BaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BaseService.class).to(BaseServiceImpl.class);
        bind(BaseRepository.class).to(BaseRepositoryImpl.class);
    }
}
