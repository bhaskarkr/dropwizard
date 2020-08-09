package com.example.projects.model;

import com.example.projects.enums.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Driver {
    private String id;
    private String name;
    private String phoneNumber;
    private String vehicleNumber;
    private Double lat;
    private Double lng;
    private DriverStatus status;
    private Boolean active;
}
