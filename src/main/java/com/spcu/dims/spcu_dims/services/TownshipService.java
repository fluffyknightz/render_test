package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Township;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;


public interface TownshipService {

    Object createTownship(String name, String district, String zone);

    List<String> showAll();

    Township findById(int id);

    Township delete(int id);

    Object update(int id, String name, String district, String zone);

    DataTablesOutput<Township> tableTownship(DataTablesInput input);
}
