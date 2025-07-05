package com.weather.app.features.shared.repository;

import com.weather.app.features.shared.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    long getLocationIdByUuid(String uuid);

    void getAll();
}
