package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ArchdioceseServices {

    Archdiocese createArchdiocese(String name, String diocese);

    List<Archdiocese> showAll();

    Archdiocese findById(int id);

    Archdiocese delete(int id);

    Archdiocese update(int id, String name, String diocese);

    DataTablesOutput<Archdiocese> tableArchdiocese(DataTablesInput input);

}
