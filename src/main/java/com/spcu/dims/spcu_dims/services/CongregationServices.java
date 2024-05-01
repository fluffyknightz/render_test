package com.spcu.dims.spcu_dims.services;


import com.spcu.dims.spcu_dims.entities.Congregation;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface CongregationServices {

    Object createCongregation(String name);

    List<String> showAll();

    Congregation findById(int id);

    Congregation delete(int id);

    Object update(int id, String name);

    DataTablesOutput<Congregation> congregationTable(DataTablesInput input);

}
