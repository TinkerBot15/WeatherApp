package com.weather.app.features.forecast.repository;

import com.weather.app.features.forecast.entity.HourlyForecast;
import org.springframework.data.repository.CrudRepository;

public interface HourlyForecastRepository extends CrudRepository<HourlyForecast, Long> {
}
