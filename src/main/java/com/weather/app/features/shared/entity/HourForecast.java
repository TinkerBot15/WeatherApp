package com.weather.app.features.shared.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.TimeZone;
import java.util.UUID;

@Entity
@Table(name = "hour_forecast")
public class HourForecast {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(unique = true, nullable = false)
        private String uuid;

        @ManyToOne
        @JoinColumn(name = "location_id", referencedColumnName = "id")
        private Location location;

        private Timestamp recordedTime;

        @Column(name = "temperature")
        private double temperature;

        @Column(name = "relative_humidity")
        private int relative_humidity;

        @Column(name = "dew_point")
        private double dewPoint;

        @Column(name = "cloud_cover")
        private int cloudCover;

        @Column(name = "rain")
        private double rain;

        @Column(name="weather_code")
        private int weather_code;

        @CreationTimestamp
        @Column(name = "created_at")
        private Timestamp createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private Timestamp updatedAt;

        public void generateUuid() { this.uuid = UUID.randomUUID().toString(); }


}
