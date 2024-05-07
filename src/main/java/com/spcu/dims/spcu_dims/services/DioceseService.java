package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Diocese;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface DioceseService {
    Object dioceseCreate(String name, String archdiocese);

    List<String> findAll();

    Diocese findById(int id);

    Diocese delete(int id);

    Object update(int id, String name, String archdiocese);

    DataTablesOutput<Diocese> tableDiocese(DataTablesInput input);

    List<String> findByArchdioceseName(String archdiocese);
}
