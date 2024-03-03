package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Region;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends DataTablesRepository<Region, Integer> {

    boolean existsByName(String name);
    Optional<Region> findByName(String name);

    Optional<Region> findById(int id);

    @Query("SELECT e.name FROM Region e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    Optional<Region> findByNameAndIdNot(String name, int id);
}
