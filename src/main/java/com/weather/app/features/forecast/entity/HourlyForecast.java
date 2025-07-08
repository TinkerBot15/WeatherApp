package com.weather.app.features.forecast.entity;

import com.weather.app.features.shared.entity.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "hour_forecast")
public class HourlyForecast {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(unique = true, nullable = false)
        private String uuid;

        @ManyToOne
        @JoinColumn(name = "city_id", referencedColumnName = "id")
        private City city;

        @Column(name = "recorded_date_time")
        private LocalDateTime recordedDateTime;

        @Column(name = "temperature")
        private double temperature;

        @Column(name = "relative_humidity")
        private int relativeHumidity;

        @Column(name = "dew_point")
        private double dewPoint;

        @Column(name = "cloud_cover")
        private int cloudCover;

        @Column(name = "rain")
        private double rain;

        @Column(name="weather_code")
        private int weatherCode;

        @CreationTimestamp
        @Column(name = "created_at")
        private Timestamp createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private Timestamp updatedAt;

        public void generateUuid() { this.uuid = UUID.randomUUID().toString(); }


}
