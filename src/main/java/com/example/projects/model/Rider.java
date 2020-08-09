package com.example.projects.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rider {
    private String id;
    private String name;
    private String phoneNumber;
    private boolean active;
    private RiderStatus status;
}
