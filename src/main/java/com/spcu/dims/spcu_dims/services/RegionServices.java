package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Region;

import java.util.List;


public interface RegionServices{

    Region createRegion(String name);

    List<Region> findAll();

    Region findById(int id);

    Region delete(int id);

    Region update(int id, String name);
}
