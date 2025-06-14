package com.basic.themePark.cities.dao;

import com.basic.themePark.cities.core.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityDao extends JpaRepository<City, UUID> {
}
