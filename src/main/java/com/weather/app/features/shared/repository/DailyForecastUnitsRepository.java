package com.weather.app.features.shared.repository;

import com.weather.app.features.shared.entity.DailyForecastUnits;
import org.springframework.data.repository.CrudRepository;

public interface DailyForecastUnitsRepository extends CrudRepository<DailyForecastUnits, Long> {
}
