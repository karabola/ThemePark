package com.basic.themePark.provinces.dao;

import com.basic.themePark.provinces.core.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinceDao extends JpaRepository<Province, UUID> {
}
