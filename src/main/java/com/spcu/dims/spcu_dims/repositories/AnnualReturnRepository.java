package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface AnnualReturnRepository extends DataTablesRepository<AnnualReturn, Integer> {
}
