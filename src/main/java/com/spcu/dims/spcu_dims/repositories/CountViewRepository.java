package com.spcu.dims.spcu_dims.repositories;


import com.spcu.dims.spcu_dims.entities.CountView;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountViewRepository extends DataTablesRepository<CountView, Integer> {
}
