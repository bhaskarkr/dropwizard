package com.example.projects.storage;

import com.example.projects.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "rides", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StoredRides {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "rider_id", referencedColumnName = "id")
    private StoredRider rider;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private StoredDriver driver;

    @Min(-180)
    @Max(180)
    @Column(name = "source_latitude", nullable = false)
    private Double pickLat;

    @Min(-90)
    @Max(90)
    @Column(name = "source_longitude", nullable = false)
    private Double pickLng;

    @Min(-180)
    @Max(180)
    @Column(name = "destination_latitude", nullable = false)
    private Double dropLat;

    @Min(-90)
    @Max(90)
    @Column(name = "destination_longitude", nullable = false)
    private Double dropLng;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus status;

}
