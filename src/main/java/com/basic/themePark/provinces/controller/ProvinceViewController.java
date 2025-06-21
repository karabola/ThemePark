package com.basic.themePark.provinces.controller;

import com.basic.themePark.ParkHelper;
import com.basic.themePark.parks.core.Park;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/provinces")
@Controller
public class ProvinceViewController {
    @Autowired
    private ParkHelper parkHelper;

    /**
     * Handles GET requests to /province/{provinceName} and displays a page
     * with a list of amusement parks located in the specified province.
     *
     * @param provinceName the province extracted from the URL path variable
     * @param model        the Model object used to pass data to the Thymeleaf view, including:
     *                     - a list of parks in the specified province,
     *                     - the formatted province for display,
     *                     - the number of parks found
     * @return the name of the Thymeleaf template "province" that renders the results
     * http://localhost:8081/themePark/provinces/slaskie
     */
    @GetMapping("/{provinceName}")
    public String showParksByProvince(@PathVariable String provinceName, Model model) {
        List<Park> parksInProvince = parkHelper.filterParksByField(p -> p.getProvince().getName().equalsIgnoreCase(provinceName), provinceName);

        model.addAttribute("parks", parksInProvince);
        model.addAttribute("selectedProvince", parkHelper.capitalizeWordsFlexible(provinceName));
        model.addAttribute("parkCount", parksInProvince.size());

        return "province";
    }
}



