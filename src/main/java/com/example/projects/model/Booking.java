package com.example.projects.model;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private String bookingId;
    private String parkingLotId;
    private String registrationNumber;
    private VehicleType type;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
