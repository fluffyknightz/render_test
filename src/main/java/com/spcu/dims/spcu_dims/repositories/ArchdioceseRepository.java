package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ArchdioceseRepository extends DataTablesRepository<Archdiocese, Integer> {


    Optional<Archdiocese> findByName(String name);

    @Query("SELECT e.name FROM Archdiocese e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    Optional<Archdiocese> findByNameAndIdNot(String name, int id);
}
