package com.example.projects.model;

import com.example.projects.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
    private Driver driver;
    private Rider rider;
    private Double pickLat;
    private Double pickLng;
    private Double dropLat;
    private Double dropLng;
    private RideStatus status;
}
