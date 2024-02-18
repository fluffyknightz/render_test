package com.spcu.dims.spcu_dims.services;


import com.spcu.dims.spcu_dims.entities.District;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface DistrictServices {
    District createDistrict(String name, String region);

    List<District> findAll();

    District findById(int id);

    District delete(int id);

    District update(int id, String name, String region);

    DataTablesOutput<District> tableDistrict(DataTablesInput input);

}
