package com.basic.themePark.mainPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class mainPageController {
    /**
     * The method handling the main page.
     *
     * @return expects the file mainPage.html
     * http://localhost:8081/themePark/main
     */
    @GetMapping
    public String home() {
        return "mainPage";
    }
}
