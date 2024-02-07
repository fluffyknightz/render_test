package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Township;
import java.util.List;


public interface TownshipServices {

    Township createTownship(String name, int districtId);

    List<Township> findAll();

    Township findById(int id);

    Township delete(int id);

    Township update(int id, String name);
}
