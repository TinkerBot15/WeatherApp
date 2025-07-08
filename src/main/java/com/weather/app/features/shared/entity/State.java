package com.weather.app.features.shared.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.weather.app.features.shared.entity.Country;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Entity
@Setter
@Getter
@NoArgsConstructor

@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column(name = "state_name")
    private String stateName;


    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @JsonIgnore
    private Country country;

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    private List<City> cities;

    @PrePersist
    public void generateUuid() {
        setUuid(UUID.randomUUID().toString());
    }
}
