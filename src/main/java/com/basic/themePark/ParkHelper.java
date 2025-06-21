package com.basic.themePark;

import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParkHelper {
    @Autowired
    private ParkService parkService;

    public <T> List<Park> filterParksByField(Function<Park, T> extractor, T value) {
        return parkService.getAllParks().stream()
                .filter(park -> Objects.equals(extractor.apply(park), value))
                .collect(Collectors.toList());
    }

    public String capitalizeWordsFlexible(String input) {
        if (input == null || input.isBlank()) return input;

        return Arrays.stream(input.trim().toLowerCase().split("(?<=\\b)[\\s-]+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
