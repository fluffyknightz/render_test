package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Parish;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ParishServices {

    Object createParish(String name, String township, String congregation, String note);

    List<String> showAll();

    Parish findById(int id);

    Parish delete(int id);

    Object update(int id, String name, String township, String congregation, String note);

    DataTablesOutput<Parish> tableParish(DataTablesInput input);

    List<String> findByZoneName(String zone);

    int parishCount();
}
