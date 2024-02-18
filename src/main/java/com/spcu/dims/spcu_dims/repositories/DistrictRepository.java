package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.District;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends DataTablesRepository<District, Integer> {

    Optional<District> findByName(String name);

    List<District> findByRegionName(String region);

}
