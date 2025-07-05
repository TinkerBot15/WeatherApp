package com.weather.app.features.shared.repository;

import com.weather.app.features.shared.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
    State findByStateName(String stateName);
}
