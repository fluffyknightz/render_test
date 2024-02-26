package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Parish;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ParishServices {

    Object createParish(String name, String zone, String township);

    List<String> showAll();

    Parish findById(int id);

    Parish delete(int id);

    Object update(int id, String name, String zone, String township);

    DataTablesOutput<Parish> tableParish(DataTablesInput input);
}
