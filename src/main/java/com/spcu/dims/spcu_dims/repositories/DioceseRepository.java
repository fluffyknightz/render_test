package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Diocese;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DioceseRepository extends DataTablesRepository<Diocese, Integer> {

    boolean existsByName(String name);

    Optional<Diocese> findByName(String name);

    @Query("SELECT e.name FROM Diocese e WHERE e.isDelete = false")
    List<String> findAllNotDeleted();

    Optional<Diocese> findByNameAndIdNot(String name, int id);
}
