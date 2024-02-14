package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Diocese;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DioceseRepository extends DataTablesRepository<Diocese, Integer> {

    boolean existsByName(String name);

    Optional<Diocese> findByName(String name);

}
