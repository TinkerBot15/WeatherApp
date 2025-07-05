package com.weather.app.features.shared.db.seeder;

import com.weather.app.features.shared.entity.Country;
import com.weather.app.features.shared.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Order(1)
public class CountrySeeder implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {

        if(countryRepository.count() == 0)
        {
            ArrayList<String> countryNames = new ArrayList<>();
            countryNames.add("Nigeria");
            countryNames.add("Ghana");
            countryNames.add("Senegal");
            countryNames.add("United States");
            countryNames.add("United Kingdom");

            ArrayList<String> countryCodes = new ArrayList<>();
            countryCodes.add("NG");
            countryCodes.add("GH");
            countryCodes.add("SE");
            countryCodes.add("US");
            countryCodes.add("UN");

            for (int i = 0; i < countryNames.size(); i++) {
                Country country = new Country();
                country.setCountryName(countryNames.get(i));
                country.setCountryCode(countryCodes.get(i));
                countryRepository.save(country);
            }

            System.out.println("Country has been seeded");
        }else {
            System.out.println("Country has already been seeded");
        }
    }
}
