package com.example.projects.model;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingConfig {

    private HashMap<VehicleType, ArrayList<Integer>> rateCard;
    private ArrayList<Integer> hourSplit;

}
