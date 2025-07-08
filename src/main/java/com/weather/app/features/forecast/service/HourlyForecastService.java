package com.weather.app.features.forecast.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.features.forecast.entity.HourlyForecast;
import com.weather.app.features.forecast.entity.HourlyForecastUnits;
import com.weather.app.features.forecast.repository.HourlyForecastRepository;
import com.weather.app.features.forecast.repository.HourlyForecastUnitsRepository;
import com.weather.app.features.shared.entity.City;
import com.weather.app.features.shared.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class HourlyForecastService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private HourlyForecastRepository hourlyForecastRepository;

    @Autowired
    private HourlyForecastUnitsRepository hourlyForecastUnitsRepository;

    @Autowired
    private RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();


    public void fetchHourlyForecastFromExternalApi() throws JsonProcessingException {
        ArrayList<City> locations = (ArrayList<City>) cityRepository.findAll();

        for (City location : locations) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String apiUrl = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,rain,weather_code,cloud_cover&forecast_days=1", latitude, longitude);
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    String.class
            );

            String rawJson = response.getBody();
            JsonNode root = objectMapper.readTree(rawJson);
            saveHourlyForecasts(root, location);
        }
    }

    public void saveHourlyForecasts(JsonNode root, City location) {
        JsonNode hourly = root.path("hourly");
        JsonNode units = root.path("hourly_units");

        int count = hourly.path("time").size();

        for (int i = 0; i < count; i++) {
            HourlyForecast forecast = new HourlyForecast();

            forecast.setCity(location);

            forecast.setRecordedDateTime(LocalDateTime.parse(hourly.path("time").get(i).asText()));

            forecast.setTemperature(hourly.path("temperature_2m").get(i).asDouble());

            forecast.setRelativeHumidity(hourly.path("relative_humidity_2m").get(i).asInt());

            forecast.setDewPoint(hourly.path("dew_point_2m").get(i).asDouble());

            forecast.setRain(hourly.path("rain").get(i).asDouble());

            forecast.setWeatherCode(hourly.path("weather_code").get(i).asInt());

            forecast.setCloudCover(hourly.path("cloud_cover").get(i).asInt());

            hourlyForecastRepository.save(forecast);

            HourlyForecastUnits unit = new HourlyForecastUnits();

            unit.setUtcOffsetSeconds(root.path("utc_offset_seconds").asInt());

            unit.setTimeZone(root.path("timezone").asText());

            unit.setTimezoneAbbreviation(root.path("timezone_abbreviation").asText());

            unit.setElevation(root.path("elevation").asInt());

            unit.setUnitTime(units.path("time").asText());

            unit.setTemperatureUnit(units.path("temperature_2m").asText());

            unit.setRelativeHumidityUnit(units.path("relative_humidity_2m").asText());

            unit.setDewPointUnit(units.path("dew_point_2m").asText());

            unit.setRainUnit(units.path("rain").asText());

            unit.setWeatherCodeUnit(units.path("weather_code").asText());

            unit.setCloudCoverUnit(units.path("cloud_cover").asText());

            unit.setHourlyForecast(forecast);

            hourlyForecastUnitsRepository.save(unit);
        }
    }

}
