package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.Institute;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface InstitutionRepository extends DataTablesRepository<Institute, Integer> {
}
