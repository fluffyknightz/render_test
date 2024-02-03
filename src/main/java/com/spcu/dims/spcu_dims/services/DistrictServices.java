package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.District;

import java.util.List;

public interface DistrictServices {
    District createDistrict(String name, int regionId);

    List<District> findAll();

    District findById(int id);

    District delete(int id);

    District update(int id, String name);
}
