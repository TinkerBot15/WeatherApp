package com.weather.app.features.shared.db.seeder;

import com.weather.app.features.shared.entity.Country;
import com.weather.app.features.shared.entity.State;
import com.weather.app.features.shared.repository.CountryRepository;
import com.weather.app.features.shared.repository.StateRepository;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Order(2)
public class StateSeeder implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    public void run(String... args) throws Exception {
       if(stateRepository.count()==0){
           ArrayList<String> states = new ArrayList<>();
           states.add("Abia");
           states.add("Adamawa");
           states.add("Akwa Ibom");
           states.add("Anambra");
           states.add("Bauchi");
           states.add("Bayelsa");
           states.add("Benue");
           states.add("Borno");
           states.add("Cross River");
           states.add("Delta");
           states.add("Ebonyi");
           states.add("Edo");
           states.add("Ekiti");
           states.add("Enugu");
           states.add("Gombe");
           states.add("Imo");
           states.add("Jigawa");
           states.add("Kaduna");
           states.add("Kano");
           states.add("Katsina");
           states.add("Kebbi");
           states.add("Kogi");
           states.add("Kwara");
           states.add("Lagos");
           states.add("Nasarawa");
           states.add("Niger");
           states.add("Ogun");
           states.add("Ondo");
           states.add("Osun");
           states.add("Oyo");
           states.add("Plateau");
           states.add("Rivers");
           states.add("Sokoto");
           states.add("Taraba");
           states.add("Yobe");
           states.add("Zamfara");

           Country Nigeria = countryRepository.findByCountryName("Nigeria");
           for(String stateName: states){
               State eachState = new State();
               eachState.setStateName(stateName);
               eachState.setCountry(Nigeria);
               stateRepository.save(eachState);
           }

           System.out.println("States have been seeded");
       }else  {
           System.out.println("State has already been seeded");
       }
   }
}
