package com.basic.ThemePark.service;

import com.basic.ThemePark.core.City;
import com.basic.ThemePark.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public City get(int idCity) {
        return cityDao.getOne(idCity);
    }

    @Override
    public Collection<City> getAllCities() {
        return cityDao.getAllCities();
    }
}
