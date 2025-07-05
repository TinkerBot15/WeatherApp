package com.weather.app.features.shared.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,  nullable = false)
    private String uuid;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code", unique = true, nullable = false)
    private String countryCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private  Timestamp updatedAt;

    @OneToMany(mappedBy = "country")
    private List<State> states;

    @PrePersist
    public void generateUuid() {    setUuid(UUID.randomUUID().toString()); }
}
