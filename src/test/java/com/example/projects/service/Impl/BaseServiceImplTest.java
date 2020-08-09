package com.example.projects.service.Impl;

import com.example.projects.BaseTest;
import com.example.projects.model.Base;
import com.example.projects.model.request.BaseCreateRequest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.example.projects.util.MockGenerator.TEST_NAME;
import static com.example.projects.util.MockGenerator.TEST_USN;

@RunWith(MockitoJUnitRunner.class)
public class BaseServiceImplTest extends BaseTest {

    @BeforeClass
    public static void setUp(){
        baseService = new BaseServiceImpl(baseRepository);
    }

    @Test
    public void createAndGetBaseSuccess() throws Exception {
        BaseCreateRequest baseCreateRequest = BaseCreateRequest.builder()
                .name(TEST_NAME)
                .usn(TEST_USN)
                .build();
        Base storedBase =  baseService.createBase(baseCreateRequest);
        Base base = baseService.getBase(storedBase.getId(), false);
        Assert.assertTrue(base.getName()==TEST_NAME);
    }
}