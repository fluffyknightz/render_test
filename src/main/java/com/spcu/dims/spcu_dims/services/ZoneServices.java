package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ZoneServices {

    Zone zoneCreate(String name);

    List<Zone> findAll();

    Zone findById(int id);

    Zone delete(int id);

    Zone update(int id, String name);

    DataTablesOutput<Zone> tableZone(DataTablesInput input);
}
