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
@GetMapping("/search")
public String searchPark(@RequestParam("parkName") String parkName) {
    if (parkName == null || parkName.isBlank()) {
        return "redirect:/main";
    }

    String nameOfPark = parkName.trim().toLowerCase();

    // 1. Sprawdź czy istnieje park o podanej nazwie
    Optional<Park> park = parkService.getAllParks().stream()
            .filter(p -> p.getName().equalsIgnoreCase(nameOfPark))
            .findFirst();

    if (park.isPresent()) {
        return "redirect:/parks/" + nameOfPark;
    }

    // 2. Sprawdź czy istnieje miasto o podanej nazwie
    Optional<City> city = cityService.getAllCities().stream()
            .filter(c -> c.getName().equalsIgnoreCase(nameOfPark))
            .findFirst();

    if (city.isPresent()) {
        return "redirect:/cities/" + nameOfPark;
    }
    // 3. Sprawdź czy istnieje województwo o podanej nazwie
    Optional<Province> province = provinceService.getAllProvinces().stream()
            .filter(c -> c.getName().equalsIgnoreCase(nameOfPark))
            .findFirst();

    if (province.isPresent()) {
        return "redirect:/provinces/" + nameOfPark;
    }

    // 3. Jeśli nic nie znaleziono → wróć na stronę główną lub pokaż błąd
    return "redirect:/main";
}

}
