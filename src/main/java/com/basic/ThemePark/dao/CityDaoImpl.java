package com.basic.ThemePark.dao;

import com.basic.ThemePark.core.City;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CityDaoImpl implements CityDao {
    private Map<Integer, City> cities;

    @Override
    public City getOne(int idCity) {
        return cities.get(idCity);
    }

    @Override
    public Collection<City> getAllCities() {
        return cities.values();
    }

    @PostConstruct
    public void initCityMap() {
        cities = new HashMap<>();
        cities.put(1, new City("Zator"));
        cities.put(2, new City("Chorzów"));
        cities.put(3, new City("Bałtów"));
    }
}
