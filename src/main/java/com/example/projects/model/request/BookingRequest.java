package com.example.projects.model.request;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private String parkingLotId;
    private String registrationNumber;
    private VehicleType type;

}
