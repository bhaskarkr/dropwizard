package com.example.projects.model;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    private String id;
    private String address;
    private HashMap<VehicleType, Integer> slotsCapacity;
    private HashMap<VehicleType, Integer> slotsAvailable;
    private boolean active;
    private Date updatedAt;
    private Date createdAt;
}
