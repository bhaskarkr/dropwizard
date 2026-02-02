package com.example.projects;

import com.example.projects.repository.BaseRepository;
import com.example.projects.repository.Impl.BaseRepositoryImpl;
import com.example.projects.service.BaseService;
import org.junit.BeforeClass;

public abstract class BaseTest {

    protected static BaseService baseService;
    protected static BaseRepository baseRepository;

    @BeforeClass
    public static void setup() {
        baseRepository = new BaseRepositoryImpl();
    }
}
