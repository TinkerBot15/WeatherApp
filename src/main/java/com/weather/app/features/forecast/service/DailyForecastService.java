package com.weather.app.features.forecast.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.app.features.forecast.entity.DailyForecast;
import com.weather.app.features.forecast.entity.DailyForecastUnits;
import com.weather.app.features.forecast.repository.DailyForecastRepository;
import com.weather.app.features.forecast.repository.DailyForecastUnitsRepository;
import com.weather.app.features.shared.entity.City;
import com.weather.app.features.shared.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DailyForecastService {

        @Autowired
        private CityRepository cityRepository;

        @Autowired
        private DailyForecastRepository dailyForecastRepository;

        @Autowired
        private DailyForecastUnitsRepository dailyForecastUnitsRepository;

        @Autowired
        private RestTemplate restTemplate;

        ObjectMapper objectMapper = new ObjectMapper();


        public void fetchDailyForecastFromExternalApi() throws JsonProcessingException {
                ArrayList<City> locations = (ArrayList<City>) cityRepository.findAll();

                for (City location : locations) {
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
                        JsonNode root = objectMapper.readTree(rawJson);
                        saveDailyForecasts(root, location);
                }
        }

        public void saveDailyForecasts(JsonNode root, City location) {
                JsonNode daily = root.path("daily");
                JsonNode units = root.path("daily_units");

                int forecastCount = daily.path("time").size();

                for (int i = 0; i < forecastCount; i++) {

                        DailyForecast forecast = new DailyForecast();

                        forecast.setCity(location);

                        forecast.setRecordedDate(LocalDate.parse(daily.path("time").get(i).asText()));

                        forecast.setTemperature(daily.path("temperature_2m_max").get(i).asDouble());

                        forecast.setRelative_humidity(daily.path("relative_humidity_2m_max").get(i).asInt());

                        forecast.setDewPoint(daily.path("dew_point_2m_max").get(i).asDouble());

                        forecast.setCloudCover(daily.path("cloud_cover_max").get(i).asInt());

                        forecast.setRain(daily.path("rain_sum").get(i).asDouble());

                        forecast.setWeatherCode(daily.path("weather_code").get(i).asInt());

                        dailyForecastRepository.save(forecast);

                        DailyForecastUnits dailyForecastUnits = new DailyForecastUnits();

                        dailyForecastUnits.setUtcOffsetSeconds(root.path("utc_offset_seconds").asInt());

                        dailyForecastUnits.setTimeZone(root.path("timezone").asText());

                        dailyForecastUnits.setTimezoneAbbreviation(root.path("timezone_abbreviation").asText());

                        dailyForecastUnits.setElevation(root.path("elevation").asInt());

                        dailyForecastUnits.setTimeUnit(units.path("time").asText());

                        dailyForecastUnits.setWeatherCodeUnit(units.path("weather_code").asText());

                        dailyForecastUnits.setTemperatureUnit(units.path("temperature_2m_max").asText());

                        dailyForecastUnits.setRainSumUnit(units.path("rain_sum").asText());

                        dailyForecastUnits.setRelativeHumidityUnit(units.path("relative_humidity_2m_max").asText());

                        dailyForecastUnits.setCloudCoverUnit(units.path("cloud_cover_max").asText());

                        dailyForecastUnits.setDailyForecast(forecast);

                        dailyForecastUnitsRepository.save(dailyForecastUnits);
                }
        }

}
