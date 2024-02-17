package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Township;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;


public interface TownshipServices {

    Township createTownship(String name, String district);

    List<Township> showAll();

    Township findById(int id);

    Township delete(int id);

    Township update(int id, String name, String district);

    DataTablesOutput<Township> tableTownship(DataTablesInput input);
}
