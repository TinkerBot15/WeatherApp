package com.weather.app.features.shared.repository;

import com.weather.app.features.shared.entity.DailyForecast;
import com.weather.app.features.shared.entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface DailyForecastRepository extends CrudRepository<DailyForecast,Long> {
    void getByLocationId(Location location);
}
