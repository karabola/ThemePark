package com.basic.themePark.admin.controller;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.service.CityService;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import com.basic.themePark.provinces.core.Province;
import com.basic.themePark.provinces.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminFormController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ParkService parkService;

    /**
     * @param model
     * @return http://localhost:8081/themePark/admin/addAll
     */
    @GetMapping("/addAll")
    public String showAddForm(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("province", new Province());
        model.addAttribute("city", new City());
        model.addAttribute("park", new Park());
        model.addAttribute("message", message); // flash attribute przekazany z RedirectAttributes
        return "addProvince_City_Park";
    }


    @PostMapping("/provinces/add")
    public String addProvince(@ModelAttribute Province province, RedirectAttributes redirectAttributes) {
        String name = province.getName().trim();

        boolean exists = provinceService.getAllProvinces().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));

        if (exists) {
            redirectAttributes.addFlashAttribute("message", "❗ Województwo „" + name + "” już istnieje.");
        } else {
            Province newProvince = new Province();
            newProvince.setName(name);
            provinceService.addProvince(newProvince);
            redirectAttributes.addFlashAttribute("message", "✅ Dodano województwo: „" + name + "”.");
        }

        return "redirect:/admin/addAll" ;
    }

    @PostMapping("/cities/add")
    public String addCity(@ModelAttribute City city,
                          @RequestParam("provinceName") String provinceName,
                          RedirectAttributes redirectAttributes) {

        String cityName = city.getName().trim();
        String provinceInput = provinceName.trim();

        Optional<Province> provinceOpt = provinceService.getAllProvinces().stream()
                .filter(p -> p.getName().equalsIgnoreCase(provinceInput))
                .findFirst();

        if (provinceOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "❗ Nie znaleziono województwa „" + provinceInput + "”.");
            return "redirect:/admin/addAll";
        }

        Province province = provinceOpt.get();

        boolean exists = cityService.getAllCities().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(cityName));

        if (exists) {
            redirectAttributes.addFlashAttribute("message", "❗ Miasto „" + cityName + "” już istnieje.");
        } else {
            City newCity = new City();
            newCity.setName(cityName);
            newCity.setProvince(province);
            cityService.addCity(newCity);
            redirectAttributes.addFlashAttribute("message", "✅ Dodano miasto: „" + cityName + "”.");
        }

        return "redirect:/admin/addAll";
    }

    @PostMapping("/parks/add")
    public String addPark(@ModelAttribute Park park,
                          @RequestParam String cityName,
                          @RequestParam String provinceName,
                          @RequestParam(required = false) String images,
                          RedirectAttributes redirectAttributes) {


        String parkName = park.getName().trim();
        String cityInput = cityName.trim();
        String provinceInput = provinceName.trim();

        boolean alreadyExists = parkService.getAllParks().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(parkName));

        if (alreadyExists) {
            redirectAttributes.addFlashAttribute("message", "❗ Park „" + parkName + "” już istnieje.");
            return "redirect:/admin/addAll";
        }

        Province province = provinceService.getAllProvinces().stream()
                .filter(p -> p.getName().equalsIgnoreCase(provinceInput))
                .findFirst()
                .orElseGet(() -> {
                    Province newProvince = new Province();
                    newProvince.setName(provinceInput);
                    provinceService.addProvince(newProvince);
                    redirectAttributes.addFlashAttribute("message", "✅ Dodano nowe województwo: " + provinceInput);
                    return newProvince;
                });

        City city = cityService.getAllCities().stream()
                .filter(c -> c.getName().equalsIgnoreCase(cityInput))
                .findFirst()
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setName(cityInput);
                    newCity.setProvince(province);
                    cityService.addCity(newCity);
                    redirectAttributes.addFlashAttribute("message", "✅ Dodano nowe miasto: " + cityInput);
                    return newCity;
                });

        park.setCity(city);
        park.setProvince(province);
        if (images != null && !images.isBlank()) {
            List<String> paths = Arrays.stream(images.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .toList();
            park.setImagesPaths(paths);
        }

        parkService.addPark(park);
        redirectAttributes.addFlashAttribute("message", "✅ Dodano park „" + parkName + "”.");
        return "redirect:/admin/addAll";
    }
}
