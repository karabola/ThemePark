package com.basic.themePark.parks.controller;

import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/parks")
@Controller
public class ParkController {
    @Autowired
    private ParkService parkService;

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
}
