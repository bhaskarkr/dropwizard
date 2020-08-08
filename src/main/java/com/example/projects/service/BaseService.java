package com.example.projects.service;

import com.example.projects.model.Base;
import com.example.projects.model.request.BaseCreateRequest;

public interface BaseService {

    Base getBase(String id, boolean allowInactive) throws Exception;

    Base createBase(BaseCreateRequest request) throws Exception;

}
