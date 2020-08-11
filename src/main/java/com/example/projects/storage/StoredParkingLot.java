package com.example.projects.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking_lot", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StoredParkingLot {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String Id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "slots_capacity", nullable = false)
    private String slotsCapacity;

    @Column(name = "slots_available", nullable = false)
    private String slotsAvailable;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updatedAt;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

}
