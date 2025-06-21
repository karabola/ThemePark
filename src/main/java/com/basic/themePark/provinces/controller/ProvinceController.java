package com.basic.themePark.provinces.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.provinces.core.Province;
import com.basic.themePark.provinces.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/provinces")
@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * The method displays a list of provinces as an HTML page;
     *
     * @return expects the file provinces.html
     * http://localhost:8081/themePark/provinces
     */
    @GetMapping
    public List<Province> getProvince() {
        return provinceService.getAllProvinces();
    }
    
}
