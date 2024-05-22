package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.views.AnnualReturnView;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.Optional;

public interface AnnualReturnViewRepository extends DataTablesRepository<AnnualReturnView, Integer> {

    Optional<AnnualReturnView> findByParishAndYear(String parish, int year);
}
