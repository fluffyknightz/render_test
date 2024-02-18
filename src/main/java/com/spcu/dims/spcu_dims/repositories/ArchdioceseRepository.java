package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchdioceseRepository extends DataTablesRepository<Archdiocese, Integer> {

    boolean existsByName(String name);

    Optional<Archdiocese> findByName(String name);
}
