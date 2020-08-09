package com.example.projects.storage;

import com.example.projects.enums.RiderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rider", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class StoredRider {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Length(min = 10, max = 10)
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RiderStatus status;

    @Column(name = "active", nullable = false)
    private Boolean active;

}
