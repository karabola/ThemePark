package com.basic.ThemePark.dao;

import com.basic.ThemePark.core.City;

import java.util.Collection;

public interface CityDao {
    City getOne(int idCity);

    Collection<City> getAllCities();
}
