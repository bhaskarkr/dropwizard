package com.example.projects.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingRequest {
    @NotNull
    private String riderId;
    @NotNull
    private String driverId;
    @Min(-180)
    @Max(180)
    @NotNull
    private Double pickLat;
    @Min(-90)
    @Max(90)
    @NotNull
    private Double pickLng;
    @Min(-180)
    @Max(180)
    @NotNull
    private Double dropLat;
    @Min(-90)
    @Max(90)
    @NotNull
    private Double dropLng;
}
