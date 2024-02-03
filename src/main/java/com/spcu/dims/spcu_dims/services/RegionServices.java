package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.entities.Region;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RegionServices{

    Region createRegion(String name);

    List<Region> findAll();

    Region findById(int id);

    Region delete(int id);

    Region update(int id, String name);
}
