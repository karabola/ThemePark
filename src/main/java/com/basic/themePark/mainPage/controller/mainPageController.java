package com.basic.themePark.mainPage.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.service.CityService;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import com.basic.themePark.provinces.core.Province;
import com.basic.themePark.provinces.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
public class mainPageController {
    @Autowired
    private ParkService parkService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProvinceService provinceService;

    /**
     * The method handling the main page.
     *
     * @return expects the file mainPage.html
     * http://localhost:8081/themePark/main
     */
    @GetMapping("/main")
    public String getMainPage() {
        return "mainPage";
    }

//    @GetMapping("/search")
//    public String searchPark(@RequestParam("parkName") String parkName) {
//        if (parkName == null || parkName.isBlank()) {
//            return "redirect:/main";
//        }
//
//        String sanitized = parkName.trim().toLowerCase();
//        return "redirect:/parks/" + sanitized;
//    }

    /**
     * Handles a search request for a park, city, or province based on the user's input.
     * <p>
     * This method examines the input string and attempts to match it—case-insensitively—
     * against known parks, cities, and provinces. The logic proceeds as follows:
     * <ul>
     *   <li>If a park with the given name exists, the user is redirected to the park's detail page.</li>
     *   <li>If a city with the given name exists, the user is redirected to a page listing all parks in that city.</li>
     *   <li>If a province with the given name exists, the user is redirected to a page listing all parks in that province.</li>
     * </ul>
     * If no match is found, the user is redirected back to the main page.
     * <p>
     * The method also URL-encodes the names using {@link java.net.URLEncoder} to ensure
     * safe redirects for names containing special characters (e.g., diacritics śląskie->%C5%9Bl%C4%85skie).
     *
     * @param parkName the name of a park, city, or province entered by the user
     * @return a redirect URL string pointing to the matched entity or to the main page if no match is found
     */
    @GetMapping("/search")
    public String searchPark(@RequestParam("parkName") String parkName) {
        if (parkName == null || parkName.isBlank()) {
            return "redirect:/main";
        }

        String nameOfPark = parkName.trim();

        Optional<Park> park = parkService.getAllParks().stream()
                .filter(p -> p.getName().equalsIgnoreCase(nameOfPark))
                .findFirst();

        if (park.isPresent()) {
            String encoded = URLEncoder.encode(nameOfPark, StandardCharsets.UTF_8);
            return "redirect:/parks/" + encoded;
        }

        Optional<City> city = cityService.getAllCities().stream()
                .filter(c -> c.getName().equalsIgnoreCase(nameOfPark))
                .findFirst();

        if (city.isPresent()) {
            String encoded = URLEncoder.encode(nameOfPark, StandardCharsets.UTF_8);
            return "redirect:/cities/" + encoded;
        }

        Optional<Province> province = provinceService.getAllProvinces().stream()
                .filter(p -> p.getName().equalsIgnoreCase(nameOfPark))
                .findFirst();

        if (province.isPresent()) {
            String encoded = URLEncoder.encode(nameOfPark, StandardCharsets.UTF_8);
            return "redirect:/provinces/" + encoded;
        }
        return "redirect:/main";
    }
}
