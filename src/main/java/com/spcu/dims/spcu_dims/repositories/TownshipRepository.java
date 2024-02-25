package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Township;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownshipRepository extends DataTablesRepository<Township, Integer> {
    boolean existsByName(String name);

    Optional<Township> findByName(String name);
    @Query("SELECT e.name FROM Township e WHERE e.isDelete = false")
    List<String> findAllNotDeleted();
    Optional<Township> findByNameAndIdNot(String name, int id);


}
