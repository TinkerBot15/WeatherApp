package com.weather.app.features.forecast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "daily_forecast_units")
public class DailyForecastUnits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String uuid;

    @OneToOne
    @JoinColumn(name="daily_forecast_id", referencedColumnName = "id")
    private DailyForecast dailyForecast;

    @Column(name = "utc_offset_seconds", nullable = false)
    private int utcOffsetSeconds;

    @Column(name="time_zone")
    private String timeZone;

    @Column(name = "timezone_abbreviation")
    private String timezoneAbbreviation;

    private int elevation;

    @Column(name = "time_unit")
    private String timeUnit;

    @Column(name = "weather_code_unit")
    private String weatherCodeUnit;

    @Column(name = "temperature_unit")
    private String temperatureUnit;

    @Column(name = "rain_sum_unit")
    private String rainSumUnit;

    @Column(name = "relative_humidity_unit")
    private String relativeHumidityUnit;

    @Column(name = "cloud_cover_unit")
    private String cloudCoverUnit;

    @Column(name = "dew_point_unit")
    private String dewPointUnit;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    public void generateUuid() { this.uuid = UUID.randomUUID().toString(); }



}
