package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Parish;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParishRepository extends DataTablesRepository<Parish, Integer> {
}
