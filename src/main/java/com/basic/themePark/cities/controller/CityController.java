package com.basic.themePark.cities.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/cities")
@Controller
public class CityController {
 
    @Autowired
    private CityService cityService;


    /**
     * The method displays a list of cities as an HTML page;
     *
     * @return expects the file cities.html
     */
    @GetMapping
    public String getCities(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "cities";
    }


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
}
