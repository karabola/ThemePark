package com.basic.themePark.parks.controller;

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
import java.util.stream.Collectors;

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
     * @return expects the file parks.html
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

        park = new Park(null, park.getName(), city, province, park.getDescription());
        parkDao.save(park);

        return ResponseEntity.ok("Park added successfully!");
    }

    /**
     *
     * @param cityName
     * @param model
     * @return
     * http://localhost:8081/themePark/parks/zator
     */
    @GetMapping("/{cityName}")
    public String getParksByCity(@PathVariable("cityName")  String cityName, Model model) {
        List<Park> collect = parkService.getAllParks().stream()
                .filter(p -> p.getCity().getName().equalsIgnoreCase(cityName))
                .collect(Collectors.toList());
        if (cityName != null || !cityName.isEmpty()) {

            model.addAttribute("parks", collect);
        } else {
            model.addAttribute("parks", parkService.getAllParks());
        }
        return "parks";
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
