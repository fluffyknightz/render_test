package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.Death;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface DeathRepository extends DataTablesRepository<Death, Integer> {
}
