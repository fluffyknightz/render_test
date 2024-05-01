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

    Optional<Parish> findByName(String name);

    Optional<Parish> findByNameAndIdNot(String name, int id);

    @Query("SELECT e.name FROM Parish e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    @Query(value = "SELECT p.name FROM Parish p WHERE p.isDelete = false and p.township.zone.name = :zone ORDER BY p.name ASC")
    List<String> findByZoneName(@Param("zone") String zone);

    @Query("select count(p) from Parish p where p.isDelete = false")
    int parishCount();

}
