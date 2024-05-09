package com.spcu.dims.spcu_dims.repositories;


import com.spcu.dims.spcu_dims.entities.Congregation;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CongregationRepository extends DataTablesRepository<Congregation, Integer> {

    Optional<Congregation> findByName(String name);

    @Query("SELECT e.name FROM Congregation e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    Optional<Congregation> findByNameAndIdNot(String name, int id);
}
