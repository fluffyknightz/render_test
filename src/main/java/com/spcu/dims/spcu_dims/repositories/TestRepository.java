package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Test;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;


public interface TestRepository extends DataTablesRepository<Test, Integer> {
}
