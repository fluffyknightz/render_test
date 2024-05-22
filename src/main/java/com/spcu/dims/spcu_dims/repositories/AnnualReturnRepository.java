package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.Optional;

public interface AnnualReturnRepository extends DataTablesRepository<AnnualReturn, Integer> {
    Optional<AnnualReturn> findByParishAndYearAndIdNot(Parish parish, int year, int id);
}
