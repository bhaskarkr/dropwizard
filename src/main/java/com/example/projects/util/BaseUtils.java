package com.example.projects.util;

import com.example.projects.model.Base;
import com.example.projects.model.request.BaseCreateRequest;
import com.example.projects.storage.StoredBase;
import io.appform.dropwizard.discovery.bundle.id.IdGenerator;

public interface BaseUtils {
    static StoredBase toDao(BaseCreateRequest request) {
        return StoredBase.builder()
                .id(IdGenerator.generate("B").getId())
                .active(true)
                .name(request.getName())
                .usn(request.getUsn())
                .build();
    }

    static Base toDto(StoredBase storedBase) {
        return Base.builder()
                .id(storedBase.getId())
                .name(storedBase.getName())
                .usn(storedBase.getUsn())
                .createdAt(storedBase.getCreatedAt())
                .updatedAt(storedBase.getUpdatedAt())
                .build();
    }

}
