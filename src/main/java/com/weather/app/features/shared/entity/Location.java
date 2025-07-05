package com.weather.app.features.shared.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String uuid = UUID.randomUUID().toString();

    private double latitude;

    private double longitude;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "time_zone")
    private String timeZone;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private State state;

    @PrePersist
    public void generateUuid() { setUuid(UUID.randomUUID().toString()); }
}
