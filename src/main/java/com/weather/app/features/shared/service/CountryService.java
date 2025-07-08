package com.weather.app.features.shared.service;

import com.weather.app.features.shared.entity.City;
import com.weather.app.features.shared.entity.Country;
import com.weather.app.features.shared.entity.State;
import com.weather.app.features.shared.repository.CountryRepository;
import com.weather.app.features.shared.repository.CityRepository;
import com.weather.app.features.shared.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;

    //list all countries and their country codes
    public List<Country> getCountriesWithCodes(){
        List<Country> countries = countryRepository.findAll();
        return countries;
    }

    //get states by country code
    @Transactional
    public List<State> getStates(String countryCode){
        Country country = countryRepository.findByCountryCode(countryCode);

        if(country == null){
            return null;
        }else {
            List<State> states = country.getStates();
            return states;
        }
    }

    //get cities tied to states
    @Transactional
    public List<City> getCities(String stateUuid){
        //find that selected state
        State state = stateRepository.findByUuid(stateUuid);
        if(state == null){
            return null;
        }else {
            List<City> cities = state.getCities();
            return cities;
        }

    }

    //get all cities and states
    @Transactional
    public HashMap<String, List<String>> getAllStatesAndCities() {

        HashMap<String, List<String>> allStatesAndCities = new HashMap<>();

        List<State> states = stateRepository.findAll();

        for (State state : states) {
            String stateName = state.getStateName();
            List<String> cityNames = new ArrayList<>();

            for (City city : state.getCities()) {
                String cityName = city.getCityName();
                cityNames.add(cityName);
            }

            allStatesAndCities.put(stateName, cityNames);
        }

        return allStatesAndCities;
    }

    //get all countries, states and cities
    @Transactional
    public HashMap<String, HashMap<String, List<String>>> getAllCountryStateCities() {
        HashMap<String, HashMap<String, List<String>>> allCountryStateCities = new HashMap<>();

        List<Country> countries = countryRepository.findAll();

        for (Country country : countries) {
            String countryName = country.getCountryName();
            HashMap<String, List<String>> stateCityMap = new HashMap<>();

            List<State> statesOfCountry = country.getStates();

            for (State state : statesOfCountry) {
                String stateName = state.getStateName();
                List<String> cityNames = new ArrayList<>();

                List<City> citiesOfState = state.getCities();

                for (City city : citiesOfState) {
                    cityNames.add(city.getCityName());
                }

                stateCityMap.put(stateName, cityNames);
            }

            allCountryStateCities.put(countryName, stateCityMap);
        }

        return allCountryStateCities;
    }
}
