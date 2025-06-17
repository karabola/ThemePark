package com.basic.themePark.parks.service;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.dao.ParkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkService {
    @Autowired
    private ParkDao parkDao;
    private City city;

    public List<Park> getAllParks() {
        return parkDao.findAll();
    }

    public List<Park> getParksByCity(String cityName) {
        return parkDao.findAll().stream()
                .filter(p -> p.getCity().getName().equalsIgnoreCase(cityName)).collect(Collectors.toList());
    }
}
