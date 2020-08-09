package com.example.projects.storage;

import com.example.projects.enums.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "driver", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StoredDriver {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Length(min = 10, max = 10)
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Min(-180)
    @Max(180)
    @Column(name = "latitude", nullable = false)
    private Double lat;

    @Min(-90)
    @Max(90)
    @Column(name = "longitude", nullable = false)
    private Double lng;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
