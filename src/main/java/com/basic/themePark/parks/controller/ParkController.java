package com.basic.themePark.parks.controller;

import com.basic.themePark.DescriptionFormatter;
import com.basic.themePark.cities.core.City;
import com.basic.themePark.cities.dao.CityDao;
import com.basic.themePark.cities.service.CityService;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.dao.ParkDao;
import com.basic.themePark.parks.service.ParkService;
import com.basic.themePark.provinces.core.Province;
import com.basic.themePark.provinces.dao.ProvinceDao;
import com.basic.themePark.provinces.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/parks")
@Controller
public class ParkController {
    @Autowired
    private ParkService parkService;
    @Autowired
    private ParkDao parkDao;
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private ProvinceService provinceService;

    /**
     * The method displays a list of parks as an HTML page;
     *
     * @return expects the file park.html
     * http://localhost:8081/themePark/parks
     */
    @GetMapping
    public String getParks(Model model) {
        model.addAttribute("parks", parkService.getAllParks());
        return "parks";
    }

    /**
     * The method displays the page with adding new park;
     *
     * @param model An object used to transfer data from the controller to the view.
     * @return expects the file parkAdd.html
     */
    @GetMapping("/add")
    public String addParkPage(Model model) {
        model.addAttribute("parkToAdd", new Park());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("provinces", provinceService.getAllProvinces());
        return "parkAdd";
    }

    /**
     * @param park
     * @return http://localhost:8081/themePark/parks/add
     */
    @PostMapping("/add")
    public ResponseEntity<String> addPark(@RequestBody Park park) {
        City city = null;
        if (cityDao.existsById(park.getCity().getId_city())) {
            city = cityDao.findById(park.getCity().getId_city()).get();
        } else {
            City newCity = new City();
            newCity.setName(park.getCity().getName());
            city = cityService.addCity(newCity);
        }

        Province province = null;
        if (provinceDao.existsById(park.getProvince().getId_province())) {
            province = provinceDao.findById(park.getProvince().getId_province()).get();
        } else {
            Province newProvince = new Province();
            newProvince.setName(park.getProvince().getName());
            province = provinceDao.save(newProvince);
        }

        park = new Park(null, park.getName(), city, province, park.getDescription(), park.getImagesPaths());
        parkDao.save(park);

        return ResponseEntity.ok("Park added successfully!");
    }

    /**
     * @param parkName
     * @param model
     * @return http://localhost:8081/themePark/parks/zatorland
     */
    @GetMapping("/{parkName}")
    public String getFilteredPark(@PathVariable String parkName, Model model) {
        if (parkName == null || parkName.isBlank()) {
            return "redirect:/parks";
        }

        Optional<Park> optionalPark = parkService.getAllParks().stream()
                .filter(p -> p.getName().equalsIgnoreCase(parkName))
                .findFirst();

        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            String formattedDescription = DescriptionFormatter.format(park.getDescription());
            park.setDescription(formattedDescription);

            model.addAttribute("park", park);
            List<String> imgPaths = parkService.getImagesForPark(park);
            model.addAttribute("imgPaths", imgPaths);

            return "park";
        }

        return "redirect:/parks";
    }

//    @GetMapping("/{query}")
//    public List<Park> getParksBySearch(@PathVariable String query) {
//        return parkService.getAllParks().stream()
//                .filter(p -> p.getName().equalsIgnoreCase(query) || p.getCity().getName().equalsIgnoreCase(query) || p.getProvince().getName().equalsIgnoreCase(query))
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/{cityName}")
//    public List<Park> getParksByCity(@PathVariable String cityName) {
//        List<Park> parksByCity = parkService.getParksByCity(cityName);
//        System.out.println("parksByCity = " + parksByCity);
//        return parksByCity;
//    }
}
