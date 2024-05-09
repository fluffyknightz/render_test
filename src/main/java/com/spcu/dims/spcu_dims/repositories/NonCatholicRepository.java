package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.NonCatholic;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface NonCatholicRepository extends DataTablesRepository<NonCatholic, Integer> {
}
