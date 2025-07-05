package com.weather.app.features.shared.repository;

import com.weather.app.features.shared.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
    Country findByCountryName(String countryName);
}
