package com.example.projects.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseCreateRequest {

    @NotNull
    private String name;
    @NotNull
    private String usn;

}
