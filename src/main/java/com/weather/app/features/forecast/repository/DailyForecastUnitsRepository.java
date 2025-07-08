package com.weather.app.features.forecast.repository;

import com.weather.app.features.forecast.entity.DailyForecastUnits;
import org.springframework.data.repository.CrudRepository;

public interface DailyForecastUnitsRepository extends CrudRepository<DailyForecastUnits, Long> {
}
