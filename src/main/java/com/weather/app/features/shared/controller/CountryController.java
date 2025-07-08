package com.weather.app.features.shared.controller;

import com.weather.app.features.shared.entity.City;
import com.weather.app.features.shared.entity.Country;
import com.weather.app.features.shared.entity.State;
import com.weather.app.features.shared.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class CountryController {

    private CountryService countryService;

    @GetMapping("/api/v1/countries")
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = countryService.getCountriesWithCodes();

        if(countries.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(countries);
    }

    @GetMapping(path ="/api/v1/{countryCode}/states")
    public ResponseEntity<List<State>> getStatesByCountry(@PathVariable String countryCode){

        List<State> statesLinkedToCountryCode = countryService.getStates(countryCode);

        if (statesLinkedToCountryCode == null || statesLinkedToCountryCode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(statesLinkedToCountryCode);
    }

    @GetMapping("/api/v1/{stateUuid}/cities")
    public ResponseEntity<List<City>> getCitiesByUuid(@PathVariable  String stateUuid){
        List<City> citiesByStateUuid = countryService.getCities(stateUuid);
        if (citiesByStateUuid == null || citiesByStateUuid.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(citiesByStateUuid);
    }

    @GetMapping("api/v1/state/cities")
    public ResponseEntity<HashMap<String, List<String>>> getAllStatesAndCities()
    {
        HashMap<String, List<String>> statesAndCities = countryService.getAllStatesAndCities();
        if (statesAndCities == null || statesAndCities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(statesAndCities);
    }

    @GetMapping("api/v1/country/states/cities")
    public ResponseEntity<HashMap<String, HashMap<String, List<String>>>> getAllCountriesAndStatesAndCities()
    {
        HashMap<String, HashMap<String, List<String>>> countriesStateAndCities = countryService.getAllCountryStateCities();
        if (countriesStateAndCities == null || countriesStateAndCities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(countriesStateAndCities);
    }

}

