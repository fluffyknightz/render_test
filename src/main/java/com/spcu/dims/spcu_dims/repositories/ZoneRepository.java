package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Zone;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends DataTablesRepository<Zone, Integer> {

    boolean existsByName(String name);

    Optional<Zone> findByName(String name);

    @Query("SELECT e.name FROM Zone e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();
    Optional<Zone> findByNameAndIdNot(String name, int id);

    @Query("SELECT z.name FROM Zone z WHERE z.diocese.name = :diocese")
    List<String> findByDioceseName(@Param("diocese") String diocese);

}
