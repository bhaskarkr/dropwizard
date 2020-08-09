package com.example.projects.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateDriverRequest {

    @NotNull
    private String name;
    @NotNull
    @Length(min = 10, max = 10)
    private String phoneNumber;
    @NotNull
    private String vehicleNumber;

    @NotNull
    @Min(-180)
    @Max(180)
    private Double lat;

    @NotNull
    @Min(-90)
    @Max(90)
    private Double lng;

}
