package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.Seminarian;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface SeminarianRepository extends DataTablesRepository<Seminarian, Integer> {
}
