package com.example.projects.service.Impl;

import com.example.projects.core.BaseException;
import com.example.projects.core.ErrorCode;
import com.example.projects.model.Base;
import com.example.projects.model.request.BaseCreateRequest;
import com.example.projects.repository.BaseRepository;
import com.example.projects.service.BaseService;
import com.example.projects.storage.StoredBase;
import com.example.projects.util.BaseUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class BaseServiceImpl implements BaseService {

    private final BaseRepository baseRepository;

    @Inject
    public BaseServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public Base getBase(String id, boolean allowInactive) throws Exception {
        Optional<StoredBase> optionalBase = baseRepository.get(id, allowInactive);
        if(!optionalBase.isPresent()) {
            throw new BaseException(ErrorCode.BASE_ID_NOT_FOUND, "Base not Found");
        }
        return BaseUtils.toDto(optionalBase.get());
    }

    @Override
    public Base createBase(BaseCreateRequest request) throws Exception {
        Optional<StoredBase> optionalStoredBase = baseRepository.save(BaseUtils.toDao(request));
        if(!optionalStoredBase.isPresent()){
            throw new BaseException(ErrorCode.BASE_NOT_SAVED, "Base not saved");
        }

        return BaseUtils.toDto(optionalStoredBase.get());
    }
}
