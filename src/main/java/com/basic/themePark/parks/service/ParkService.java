package com.basic.themePark.parks.service;

import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.dao.ParkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkService {
    @Autowired
    private ParkDao parkDao;

    public List<Park> getAllParks() {
        return parkDao.findAll();
    }
}
