package com.basic.themePark.cities.controller;

import com.basic.themePark.ParkHelperNEW;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/cities")
@Controller
public class CityViewController {
    @Autowired
    private ParkHelperNEW parkHelperNEW;
    @Autowired
    private ParkService parkService;

    /**
     * Handles GET requests to /cities/{cityName} and displays a page
     * with a list of amusement parks located in the specified city.
     *
     * @param cityName the name of the city extracted from the URL path variable
     * @param model    the Model object used to pass data to the Thymeleaf view, including:
     *                 - a list of parks in the specified city,
     *                 - the formatted city name for display,
     *                 - the number of parks found
     * @return the name of the Thymeleaf template "city" that renders the results
     * http://localhost:8081/themePark/cities/zator
     */
    @GetMapping("/{cityName}")
    public String showParksByCity(@PathVariable String cityName, Model model) {
        String simplifyCityName = parkHelperNEW.simplify(cityName);
        
        List<Park> parksInCity = parkService.getAllParks().stream()
                .filter(p -> parkHelperNEW.simplify(p.getCity().getName()).equals(simplifyCityName))
                .collect(Collectors.toList());
        model.addAttribute("parks", parksInCity);
        model.addAttribute("selectedCity", parkHelperNEW.capitalizeWordsFlexible(cityName));
        model.addAttribute("parkCount", parksInCity.size());

        return "city";
    }




}
