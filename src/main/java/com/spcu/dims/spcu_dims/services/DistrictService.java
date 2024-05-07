package com.spcu.dims.spcu_dims.services;


import com.spcu.dims.spcu_dims.entities.District;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface DistrictService {
    Object createDistrict(String name, String region);

    List<String> findAll();

    District findById(int id);

    District delete(int id);

    Object update(int id, String name, String region);

    DataTablesOutput<District> tableDistrict(DataTablesInput input);

    List<String> findByRegionName(String region);

}
