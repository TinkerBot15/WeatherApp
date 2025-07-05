package com.weather.app.features.shared.db.seeder;

import com.weather.app.features.shared.entity.Location;
import com.weather.app.features.shared.entity.State;
import com.weather.app.features.shared.repository.LocationRepository;
import com.weather.app.features.shared.repository.StateRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Order(3)
public class LocationSeeder implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RestTemplate restTemplate;

        @Override
        public void run(String... args) throws Exception {

            if(locationRepository.count()==0){
                createCities();
                System.out.println("Cities have been seeded with their coordinates");
            }
        }

        public void createCities()
        {
            ArrayList<State> allStates = (ArrayList<State>) stateRepository.findAll();

            HashMap<String, ArrayList<String>> locations = new HashMap<>();
            for(State state : allStates){

                String eachState = state.getStateName();
                ArrayList<String> eachStateCities = new ArrayList<>();

                if (Objects.equals(eachState, "Abia")) {
                    eachStateCities.add("Umuahia");
                    eachStateCities.add("Aba");
                    eachStateCities.add("Ohafia");
                } else if (Objects.equals(eachState, "Adamawa")) {
                    eachStateCities.add("Yola");
                    eachStateCities.add("Mubi");
                    eachStateCities.add("Jimeta");
                } else if (Objects.equals(eachState, "Akwa Ibom")) {
                    eachStateCities.add("Uyo");
                    eachStateCities.add("Eket");
                    eachStateCities.add("Ikot Ekpene");
                } else if (Objects.equals(eachState, "Anambra")) {
                    eachStateCities.add("Awka");
                    eachStateCities.add("Onitsha");
                    eachStateCities.add("Nnewi");
                } else if (Objects.equals(eachState, "Bauchi")) {
                    eachStateCities.add("Bauchi");
                    eachStateCities.add("Azare");
                    eachStateCities.add("Misau");
                } else if (Objects.equals(eachState, "Bayelsa")) {
                    eachStateCities.add("Yenagoa");
                    eachStateCities.add("Brass");
                    eachStateCities.add("Sagbama");
                } else if (Objects.equals(eachState, "Benue")) {
                    eachStateCities.add("Makurdi");
                    eachStateCities.add("Gboko");
                    eachStateCities.add("Otukpo");
                } else if (Objects.equals(eachState, "Borno")) {
                    eachStateCities.add("Maiduguri");
                    eachStateCities.add("Biu");
                    eachStateCities.add("Dikwa");
                } else if (Objects.equals(eachState, "Cross River")) {
                    eachStateCities.add("Calabar");
                    eachStateCities.add("Ogoja");
                    eachStateCities.add("Ikom");
                } else if (Objects.equals(eachState, "Delta")) {
                    eachStateCities.add("Asaba");
                    eachStateCities.add("Warri");
                    eachStateCities.add("Sapele");
                } else if (Objects.equals(eachState, "Ebonyi")) {
                    eachStateCities.add("Abakaliki");
                    eachStateCities.add("Afikpo");
                    eachStateCities.add("Onueke");
                } else if (Objects.equals(eachState, "Edo")) {
                    eachStateCities.add("Benin City");
                    eachStateCities.add("Ekpoma");
                    eachStateCities.add("Auchi");
                } else if (Objects.equals(eachState, "Ekiti")) {
                    eachStateCities.add("Ado Ekiti");
                    eachStateCities.add("Ikere");
                    eachStateCities.add("Oye");
                } else if (Objects.equals(eachState, "Enugu")) {
                    eachStateCities.add("Enugu");
                    eachStateCities.add("Nsukka");
                    eachStateCities.add("Agbani");
                } else if (Objects.equals(eachState, "Gombe")) {
                    eachStateCities.add("Gombe");
                    eachStateCities.add("Kaltungo");
                    eachStateCities.add("Dukku");
                } else if (Objects.equals(eachState, "Imo")) {
                    eachStateCities.add("Owerri");
                    eachStateCities.add("Okigwe");
                    eachStateCities.add("Orlu");
                } else if (Objects.equals(eachState, "Jigawa")) {
                    eachStateCities.add("Dutse");
                    eachStateCities.add("Hadejia");
                    eachStateCities.add("Gumel");
                } else if (Objects.equals(eachState, "Kaduna")) {
                    eachStateCities.add("Kaduna");
                    eachStateCities.add("Zaria");
                    eachStateCities.add("Kafanchan");
                } else if (Objects.equals(eachState, "Kano")) {
                    eachStateCities.add("Kano City");
                    eachStateCities.add("Fagge");
                    eachStateCities.add("Gwale");
                } else if (Objects.equals(eachState, "Katsina")) {
                    eachStateCities.add("Katsina");
                    eachStateCities.add("Daura");
                    eachStateCities.add("Funtua");
                } else if (Objects.equals(eachState, "Kebbi")) {
                    eachStateCities.add("Birnin Kebbi");
                    eachStateCities.add("Argungu");
                    eachStateCities.add("Yauri");
                } else if (Objects.equals(eachState, "Kogi")) {
                    eachStateCities.add("Lokoja");
                    eachStateCities.add("Idah");
                    eachStateCities.add("Omala");
                } else if (Objects.equals(eachState, "Kwara")) {
                    eachStateCities.add("Ilorin");
                    eachStateCities.add("Offa");
                    eachStateCities.add("Patigi");
                } else if (Objects.equals(eachState, "Lagos")) {
                    eachStateCities.add("Ikeja");
                    eachStateCities.add("Lekki");
                    eachStateCities.add("Yaba");
                } else if (Objects.equals(eachState, "Nasarawa")) {
                    eachStateCities.add("Lafia");
                    eachStateCities.add("Keffi");
                    eachStateCities.add("Akwanga");
                } else if (Objects.equals(eachState, "Niger")) {
                    eachStateCities.add("Minna");
                    eachStateCities.add("Bida");
                    eachStateCities.add("Suleja");
                } else if (Objects.equals(eachState, "Ogun")) {
                    eachStateCities.add("Abeokuta");
                    eachStateCities.add("Ijebu Ode");
                    eachStateCities.add("Sagamu");
                } else if (Objects.equals(eachState, "Ondo")) {
                    eachStateCities.add("Akure");
                    eachStateCities.add("Ondo Town");
                    eachStateCities.add("Owo");
                } else if (Objects.equals(eachState, "Osun")) {
                    eachStateCities.add("Osogbo");
                    eachStateCities.add("Ife");
                    eachStateCities.add("Ilesa");
                } else if (Objects.equals(eachState, "Oyo")) {
                    eachStateCities.add("Ibadan");
                    eachStateCities.add("Ogbomosho");
                    eachStateCities.add("Oyo Town");
                } else if (Objects.equals(eachState, "Plateau")) {
                    eachStateCities.add("Jos");
                    eachStateCities.add("Pankshin");
                    eachStateCities.add("Barkin Ladi");
                } else if (Objects.equals(eachState, "Rivers")) {
                    eachStateCities.add("Port Harcourt");
                    eachStateCities.add("Bonny");
                    eachStateCities.add("Okrika");
                } else if (Objects.equals(eachState, "Sokoto")) {
                    eachStateCities.add("Sokoto");
                    eachStateCities.add("Tambuwal");
                    eachStateCities.add("Wurno");
                } else if (Objects.equals(eachState, "Taraba")) {
                    eachStateCities.add("Jalingo");
                    eachStateCities.add("Wukari");
                    eachStateCities.add("Bali");
                } else if (Objects.equals(eachState, "Yobe")) {
                    eachStateCities.add("Damaturu");
                    eachStateCities.add("Potiskum");
                    eachStateCities.add("Nguru");
                } else if (Objects.equals(eachState, "Zamfara")) {
                    eachStateCities.add("Gusau");
                    eachStateCities.add("Kaura Namoda");
                    eachStateCities.add("Talata Mafara");
                } else if (Objects.equals(eachState, "Federal Capital Territory")) {
                    eachStateCities.add("Abuja");
                    eachStateCities.add("Garki");
                    eachStateCities.add("Maitama");
                }
                locations.put(eachState,eachStateCities);
            }

            for (Map.Entry<String, ArrayList<String>> entry : locations.entrySet()) {
                String stateName = entry.getKey();
                ArrayList<String> cityList = entry.getValue();

                State state = stateRepository.findByStateName(stateName);

                if (state != null) {
                    for (String cityName : cityList) {
                        Location location = new Location();
                        location.setLocationName(cityName);
                        location.setState(state);

                      HashMap<String, double[]> cityCoordinates = new HashMap<>();

                        double [] cityCoords = addCitiesCoordinates(cityName);

//                        {"lat":234, "lon":123}
                        cityCoordinates.put(cityName, cityCoords);

                        int i = 0;
                        double latitude = cityCoordinates.get(cityName)[i];
                        double longitude = cityCoordinates.get(cityName)[i+1];
                        location.setLatitude(latitude);
                        location.setLongitude(longitude);
                        locationRepository.save(location);
                    }
                } else {
                    System.out.println("State not found: " + stateName);
                }
            }
        }

        public double[] addCitiesCoordinates(String locationName) throws JSONException {

                    String encodedLocation = URLEncoder.encode(locationName, StandardCharsets.UTF_8);
                    String apiUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + encodedLocation + "&count=1";

                ResponseEntity<String> response = restTemplate.exchange(
                        apiUrl,
                        HttpMethod.GET,
                        null,
                        String.class
                );

                double [] coordinates = new double[2];

                JSONObject json = new JSONObject(response.getBody());
                 if (json.has("results")) {
                        JSONArray results = json.getJSONArray("results");
                        if (!results.isEmpty()) {
                            JSONObject result = results.getJSONObject(0);
                            double lat = result.getDouble("latitude");
                            double lon = result.getDouble("longitude");
                            coordinates = new double[] {lat,lon};
                        } else {
                            System.out.println("No coordinates found for " + locationName);
                            coordinates = new double[0];
                        }
                    }
                return coordinates;
        }
}
