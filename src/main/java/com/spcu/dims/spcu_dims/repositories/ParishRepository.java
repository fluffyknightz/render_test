package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Parish;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParishRepository extends DataTablesRepository<Parish, Integer> {
    boolean existsByName(String name);

    Optional<Parish> findByName(String name);

    Optional<Parish> findByNameAndIdNot(String name, int id);

    @Query("SELECT e.name FROM Parish e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    @Query(value = "SELECT p.name FROM Parish p WHERE p.township.zone.name = :zone ORDER BY p.name ASC")
    List<String> findByZoneName(@Param("zone") String zone);

}
