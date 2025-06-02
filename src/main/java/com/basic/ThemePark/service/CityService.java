package com.basic.ThemePark.service;

import com.basic.ThemePark.core.City;

import java.util.Collection;

public interface CityService {
    City get(int idCity);

    Collection<City> getAllCities();
}
