package com.example.projects.storage;

import com.example.projects.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoredBooking {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "parking_id")
    private String parkingId;

    @Column(name = "registration_id")
    private String registrationId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "active")
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
