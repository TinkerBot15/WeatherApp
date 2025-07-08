package com.weather.app.features.forecast.entity;

import com.weather.app.features.shared.entity.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "daily_forecast")
public class DailyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToOne(mappedBy = "dailyForecast")
    private DailyForecastUnits dailyForecastUnits;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

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
    private int weatherCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void generateUuid() { this.uuid = UUID.randomUUID().toString(); }
}
