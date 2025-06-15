package com.basic.themePark.provinces.controller;

import com.basic.themePark.provinces.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/provinces")
@Controller
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
    public String getProvinces(Model model) {
        model.addAttribute("provinces", provinceService.getAllProvinces());
        return "provinces";
    }
}
