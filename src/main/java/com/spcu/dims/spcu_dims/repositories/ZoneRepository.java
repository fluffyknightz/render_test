package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ZoneRepository extends DataTablesRepository<Zone, Integer> {

    Optional<Zone> findByName(String name);

    @Query("SELECT e.name FROM Zone e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    Optional<Zone> findByNameAndIdNot(String name, int id);

    @Query("SELECT z.name FROM Zone z WHERE z.isDelete = false and z.diocese.name = :diocese ORDER BY z.name ASC")
    List<String> findByDioceseName(@Param("diocese") String diocese);

}
