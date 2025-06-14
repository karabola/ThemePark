package com.basic.themePark.parks.dao;

import com.basic.themePark.parks.core.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkDao extends JpaRepository<Park, UUID> {
}
