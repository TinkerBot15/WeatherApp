package com.weather.app.features.shared.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.features.shared.entity.DailyForecastUnits;
import com.weather.app.features.shared.entity.Location;
import com.weather.app.features.shared.repository.DailyForecastUnitsRepository;
import com.weather.app.features.shared.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class DailyForecastService {

        @Autowired
        private LocationRepository locationRepository;

        @Autowired
        private DailyForecastUnitsRepository dailyForecastUnitsRepository;

        @Autowired
        private RestTemplate restTemplate;

        ObjectMapper objectMapper = new ObjectMapper();


        public void createDailyForecast() throws JsonProcessingException {
                ArrayList<Location> locations = (ArrayList<Location>) locationRepository.findAll();

                for (Location location : locations) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        String apiUrl = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=weather_code,temperature_2m_max,rain_sum,relative_humidity_2m_max,cloud_cover_max,dew_point_2m_max&forecast_days=16", latitude, longitude);
                        ResponseEntity<String> response = restTemplate.exchange(
                                apiUrl,
                                HttpMethod.GET,
                                null,
                                String.class
                        );

                        String rawJson = response.getBody();

                        JsonNode node = objectMapper.readTree(rawJson);

                        DailyForecastUnits dailyForecastUnits = new DailyForecastUnits();

                        int utcOffsetSeconds = node.get("utc_offset_seconds").asInt();
                        dailyForecastUnits.setUtcOffsetSeconds(utcOffsetSeconds);

                        String timeZone = node.get("timezone").asText();
                        dailyForecastUnits.setTimeZone(timeZone);

                        String timeZoneAbbrev = node.get("timezone_abbreviation").asText();
                        dailyForecastUnits.setTimezoneAbbreviation(timeZoneAbbrev);

                        int elevation = node.get("elevation").asInt();
                        dailyForecastUnits.setElevation(elevation);

                        String timeUnit = node.get("time_unit").asText();
                        dailyForecastUnits.setTimeUnit(timeUnit);

                        String weatherCodeUnit = node.get("weather_code_unit").asText();
                        dailyForecastUnits.setWeatherCodeUnit(weatherCodeUnit);

                        String temperatureUnit = node.get("temperature_unit").asText();
                        dailyForecastUnits.setTemperatureUnit(temperatureUnit);

                        String rain = node.get("rain_sum_unit").asText();
                        dailyForecastUnits.setRainSumUnit(rain);

                        String relativeHumidity = node.get("relative_humidity_unit").asText();
                        dailyForecastUnits.setRelativeHumidityUnit(relativeHumidity);

                        String cloudCoverUnit = node.get("cloud_cover_unit").asText();
                        dailyForecastUnits.setCloudCoverUnit(cloudCoverUnit);

                        dailyForecastUnitsRepository.save(dailyForecastUnits);
                }
        }
}
