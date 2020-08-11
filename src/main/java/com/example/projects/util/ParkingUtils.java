package com.example.projects.util;

import com.example.projects.enums.VehicleType;
import com.example.projects.model.ParkingLot;
import com.example.projects.model.request.OnboardParkingLotRequest;
import com.example.projects.storage.StoredParkingLot;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appform.dropwizard.discovery.bundle.id.IdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class ParkingUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static StoredParkingLot toDao(OnboardParkingLotRequest request) {
        return StoredParkingLot.builder()
                .Id(IdGenerator.generate("P").getId())
                .active(true)
                .address(request.getAddress())
                .slotsAvailable(convertHashMapToString(request.getSlotsAvailable()))
                .slotsCapacity(convertHashMapToString(request.getSlotsCapacity()))
                .build();
    }

    public static String convertHashMapToString(HashMap hashMap) {
        String convertedValue = null;
        try{
            convertedValue = mapper.writeValueAsString(hashMap);
        } catch (Exception e){
            log.error("error while mapping hashmap to string");
        }
        return convertedValue;
    }

    public static HashMap convertStringToHashMap(String value) {
        HashMap hashMap = null;
        try{
            hashMap = mapper.readValue(value, new TypeReference<HashMap<VehicleType, Integer> >(){});
        } catch (Exception e){
            log.error("error while mapping hashmap to string");
        }
        return hashMap;
    }

    public static ParkingLot toDto(StoredParkingLot storedParkingLot) {
        return ParkingLot.builder()
                .id(storedParkingLot.getId())
                .slotsAvailable(convertStringToHashMap(storedParkingLot.getSlotsAvailable()))
                .slotsCapacity(convertStringToHashMap(storedParkingLot.getSlotsCapacity()))
                .active(storedParkingLot.isActive())
                .address(storedParkingLot.getAddress())
                .createdAt(storedParkingLot.getCreatedAt())
                .updatedAt(storedParkingLot.getUpdatedAt())
                .build();
    }
}
