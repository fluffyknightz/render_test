package com.spcu.dims.spcu_dims.repositories;


import com.spcu.dims.spcu_dims.entities.Region;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends DataTablesRepository<Region, Integer> {

    boolean existsByName(String name);
    Optional<Region> findByName(String name);

    Optional<Region> findById(int id);
}
