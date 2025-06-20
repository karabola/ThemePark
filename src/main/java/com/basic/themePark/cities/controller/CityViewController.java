package com.basic.themePark.cities.controller;

import com.basic.themePark.ParkHelper;
import com.basic.themePark.parks.core.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/cities")
@Controller
public class CityViewController {
    @Autowired
    private ParkHelper parkHelper;

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
        List<Park> parksInCity = parkHelper.filterParksByField(p -> p.getCity().getName().equalsIgnoreCase(cityName), cityName);

        model.addAttribute("parks", parksInCity);
        model.addAttribute("selectedCity", parkHelper.capitalizeWordsFlexible(cityName));
        model.addAttribute("parkCount", parksInCity.size());

        return "city";
    }
}
