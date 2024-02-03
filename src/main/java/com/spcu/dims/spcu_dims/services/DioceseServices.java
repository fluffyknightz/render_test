package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Diocese;

import java.util.List;

public interface DioceseServices {
    Diocese dioceseCreate(String name);
    List<Diocese> findAll();

    Diocese findById(int id);

    Diocese delete(int id);

    Diocese update(int id, String name);
}
