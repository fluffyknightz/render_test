package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ArchdioceseServices {

    Object createArchdiocese(String name);

    List<String> showAll();

    Archdiocese findById(int id);

    Archdiocese delete(int id);

    Object update(int id, String name);

    DataTablesOutput<Archdiocese> tableArchdiocese(DataTablesInput input);

}
