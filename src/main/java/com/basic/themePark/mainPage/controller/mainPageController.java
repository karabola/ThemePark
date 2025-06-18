package com.basic.themePark.mainPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RequestMapping("/main")
@Controller
public class mainPageController {
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

    @GetMapping("/search")
    public String searchPark(@RequestParam("parkName") String parkName) {
        if (parkName == null || parkName.isBlank()) {
            return "redirect:/main";
        }

        String sanitized = parkName.trim().toLowerCase();
        return "redirect:/parks/" + sanitized;
    }
}
