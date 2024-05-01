package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Region;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;


public interface RegionServices {

    Object createRegion(String name);

    List<String> findAll();

    Region findById(int id);

    Region delete(int id);

    Object update(int id, String name);

    DataTablesOutput<Region> tableRegion(DataTablesInput input);
}
