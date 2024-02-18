package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParishRepository extends DataTablesRepository<Parish, Integer> {
    boolean existsByName(String name);

    Optional<Parish> findByName(String name);
}
