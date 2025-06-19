package com.basic.themePark.provinces.controller;

import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/provinces")
@Controller
public class ProvinceViewController {
    @Autowired
    private ParkService parkService;

    /**
     * pobera listę parków w konkretnym województwie
     *
     * @param 
     * @param model
     * @return
     */
    @GetMapping("/{provinceName}")
    public String showParksByCity(@PathVariable String provinceName, Model model) {
        
        List<Park> parksInProvince = parkService.getAllParks().stream()
                .filter(p -> p.getProvince().getName().equalsIgnoreCase(provinceName))
                .collect(Collectors.toList());

        model.addAttribute("parks", parksInProvince);
        model.addAttribute("selectedProvince", capitalizeWordsFlexible(provinceName));
        model.addAttribute("parkCount", parksInProvince.size());

        return "province";
    }

    private String capitalizeWordsFlexible(String input) {
        if (input == null || input.isBlank()) return input;

        return Arrays.stream(input.trim().toLowerCase().split("(?<=\\b)[\\s-]+"))
                .map(word -> word.length() > 0
                        ? word.substring(0, 1).toUpperCase() + word.substring(1)
                        : "")
                .collect(Collectors.joining(" "));
    }
}
