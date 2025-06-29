package com.basic.themePark.provinces.service;

import com.basic.themePark.provinces.core.Province;
import com.basic.themePark.provinces.dao.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceDao provinceDao;

    public List<Province> getAllProvinces() {
        return provinceDao.findAll();
    }

    @Transactional
    public Province addProvince(Province province) {
        if (province.getName() != null) {
            provinceDao.save(province);
        }
        return province;
    }
}
