package com.weather.app.features.forecast.repository;

import com.weather.app.features.forecast.entity.DailyForecast;
import org.springframework.data.repository.CrudRepository;

public interface DailyForecastRepository extends CrudRepository<DailyForecast,Long> {
}
