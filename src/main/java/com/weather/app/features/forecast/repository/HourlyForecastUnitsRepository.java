package com.weather.app.features.forecast.repository;

import com.weather.app.features.forecast.entity.HourlyForecastUnits;
import org.springframework.data.repository.CrudRepository;

public interface HourlyForecastUnitsRepository extends CrudRepository<HourlyForecastUnits, Long> {
}
