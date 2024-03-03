package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.District;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends DataTablesRepository<District, Integer> {

    Optional<District> findByName(String name);
    @Query("SELECT d.name FROM District d WHERE d.region.name = :region")
    List<String> findByRegionName(@Param("region") String region);

    @Query("SELECT e.name FROM District e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();
    Optional<District> findByNameAndIdNot(String name, int id);

}
