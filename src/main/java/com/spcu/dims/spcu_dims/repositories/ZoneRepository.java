package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoneRepository extends DataTablesRepository<Zone, Integer> {

    boolean existsByName(String name);

    Optional<Zone> findByName(String name);
}
