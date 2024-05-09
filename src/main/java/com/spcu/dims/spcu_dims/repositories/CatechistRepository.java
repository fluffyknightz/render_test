package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.annual_return.Catechist;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface CatechistRepository extends DataTablesRepository<Catechist, Integer> {
}
