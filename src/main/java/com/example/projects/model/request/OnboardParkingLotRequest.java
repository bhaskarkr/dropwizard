package com.example.projects.model.request;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnboardParkingLotRequest {
        private String address;
        private HashMap<VehicleType, Integer> slotsCapacity;
        private HashMap<VehicleType, Integer> slotsAvailable;
}
