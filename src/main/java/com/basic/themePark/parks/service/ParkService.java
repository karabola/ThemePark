package com.basic.themePark.parks.service;

import com.basic.themePark.cities.core.City;
import com.basic.themePark.parks.core.Park;
import com.basic.themePark.parks.dao.ParkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParkService {
    @Autowired
    private ParkDao parkDao;
    private City city;

    public List<Park> getAllParks() {
        return parkDao.findAll();
    }

    public List<Park> getParksByCity(String cityName) {
        return parkDao.findAll().stream()
                .filter(p -> p.getCity().getName().equalsIgnoreCase(cityName)).collect(Collectors.toList());
    }

    public List<String> getImagesForPark(Park park) {
        String parkName = park.getName().toLowerCase().replaceAll("\\s+", "");
        String directoryPath = "src/main/resources/static/parkImages";

        try (Stream<Path> files = Files.list(Paths.get(directoryPath))) {
            return files
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.startsWith(parkName))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return List.of();
        }
    }

    @Transactional
    public Park addPark(Park park) {
        if (park.getName() != null) {
            parkDao.save(park);
        }
        return park;
    }

}
