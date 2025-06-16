package com.basic.themePark.cities.service;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityDao cityDao;

    public List<City> getAllCities() {
        return cityDao.findAll();
    }

    @Transactional
    public City addCity(City city) {
        if (city.getName() != null) {
            cityDao.save(city);
        }
        return city;
    }
}
