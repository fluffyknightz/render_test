package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownshipRepository extends DataTablesRepository<Township, Integer> {
    boolean existsByName(String name);

    Optional<Township> findByName(String name);
}
