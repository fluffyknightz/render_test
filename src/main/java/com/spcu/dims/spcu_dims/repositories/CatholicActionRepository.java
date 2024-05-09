package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.CatholicAction;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface CatholicActionRepository extends DataTablesRepository<CatholicAction, Integer> {
}
