package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ZoneServices {

    Object createZone(String name, String diocese);

    List<String> findAll();

    Zone findById(int id);

    Zone delete(int id);

    Object update(int id, String name, String diocese);

    DataTablesOutput<Zone> tableZone(DataTablesInput input);
}
