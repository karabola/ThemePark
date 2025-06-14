package com.basic.themePark;

import com.basic.themePark.cities.CityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
@EnableConfigurationProperties(value = CityProperties.class)
public class ThemeParkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThemeParkApplication.class, args);
    }

//    @GetMapping
//    RedirectView home() {
//        return new RedirectView("cities");
//    }
}
