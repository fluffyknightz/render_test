package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface AnnualReturnService {

    Object createAnnualReturn(AnnualReturnDTO annualReturnDTO);

    List<String> showAll();

    AnnualReturn findById(int id);

    AnnualReturn delete(int id);

    Object update(AnnualReturnDTO annualReturnDTO);

    DataTablesOutput<AnnualReturn> annualReturnTable(DataTablesInput input);


}
