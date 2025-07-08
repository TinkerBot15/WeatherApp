package com.weather.app.features.shared.repository;


import com.weather.app.features.shared.entity.Country;
import com.weather.app.features.shared.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryCode(String countryCode);
    Country findByCountryName(String countryName);
    List<State> getStatesByCountryCode(String countryCode);
    List<String> getStatesUuidByCountryCode(String countryCode);

}
