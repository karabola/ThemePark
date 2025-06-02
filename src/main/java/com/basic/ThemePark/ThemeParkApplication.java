package com.basic.ThemePark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class ThemeParkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemeParkApplication.class, args);
	}
//	@GetMapping
//	public String home() {
//		return "HELLO";
//	}
	@GetMapping
	RedirectView home() {
		return new RedirectView("cities/cityName");
	}
}
