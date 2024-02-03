package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.entities.Parish;

import java.util.List;

public interface ParishServices {

    Parish createParish(String name, int archdioceseId);

    List<Parish> showAll();

    Parish findById(int id);

    Parish delete(int id);

    Parish update(int id, String name);
}
