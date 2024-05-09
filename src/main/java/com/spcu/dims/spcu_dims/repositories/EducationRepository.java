package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.Education;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface EducationRepository extends DataTablesRepository<Education, Integer> {
}
