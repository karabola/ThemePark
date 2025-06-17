package com.basic.themePark.cities.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.service.CityService;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import java.util.List;
//import java.util.Optional;
//@RequestMapping("/cities")
//@Controller
//public class CityViewController {
//    @Autowired
//    private CityService cityService;
//    @Autowired
//    private ParkService parkService;

    /**
     * pobera listę parków w konkretnym miescie
     *
     * @param cityName
     * @param model
     * @return
     */
//    @GetMapping("/{cityName}")
//    public String getCityPage(@PathVariable("cityName") String cityName, Model model) {
//        Optional<City> city = cityService.getAllCities().stream()
//                .filter(c -> c.getName().equalsIgnoreCase(cityName))
//                .findFirst();
//        List<Park> parks = parkService.getParksByCity(cityName);
//        parks.forEach(p -> System.out.println("Park: " + p.getName() + " - " + p.getDescription()));
//        System.out.println("Przekazywane parki do Thymeleaf: " + model.getAttribute("parks"));
//
//        if (city.isPresent()) {
//            model.addAttribute("city", city.get());
//            model.addAttribute("parks", parks);
//            return "city";
//        } else {
//            return "error";
//        }
//    }
//}
