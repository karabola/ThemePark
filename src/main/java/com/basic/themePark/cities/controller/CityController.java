package com.basic.themePark.cities.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.dao.CityDao;
import com.basic.themePark.cities.service.CityService;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/cities")
@RestController
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ParkService parkService;

    /**
     * The method displays a list of cities as an HTML page;
     *
     * @return expects the file cities.html
     * http://localhost:8081/themePark/cities
     */
//    @GetMapping
//    public List<City> getCities() {
//        List<City> allCities = cityService.getAllCities();
//        allCities.forEach(c-> System.out.println("City: "+c.getName()));
//        return allCities;
//    }

    /**
     * The method answers on the HTTP GET request, which was sent to http://localhost:8081/themePark/cities/search/{cityName}
     *
     * @param name city name e.g. Zator
     * @return list of cities
     * http://localhost:8081/themePark/cities/search/zator
     */
    @GetMapping("/search/{cityName}")
    public Collection<City> getCitiesFiltered(@PathVariable("cityName") String name) {

        return cityService.getAllCities().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCity(@RequestBody City city) {
        cityService.addCity(city);
        return ResponseEntity.ok("The new city has been added successfully!");
    }

//    /**       przeniesione z parkController
//     * @param cityName
//     * @param model
//     * @return http://localhost:8081/themePark/parks/zator
//     */
//    @GetMapping("/{cityName}")
//    public String getParksByCity(@PathVariable("cityName") String cityName, Model model) {
//        List<Park> parksInCity = parkService.getAllParks().stream()
//                .filter(p -> p.getCity().getName().equalsIgnoreCase(cityName))
//                .collect(Collectors.toList());
//        if (cityName != null || !cityName.isEmpty()) {
//
//            model.addAttribute("parks", parksInCity);
//        } else {
//            model.addAttribute("parks", parkService.getAllParks());
//        }
//        return "parks";
//    }
}
