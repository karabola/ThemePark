package com.basic.ThemePark.controller;

import com.basic.ThemePark.core.City;
import com.basic.ThemePark.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/cities")
@RestController
public class CityController {
    @Value("${cityName}")
    private String cityName;
    @Autowired
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cityName")
    public String getCityName() {
        return cityName;
    }

    @GetMapping
    public Collection<City> getAll() {
        Collection<City> allCities = cityService.getAllCities();
        return allCities;
    }
}
