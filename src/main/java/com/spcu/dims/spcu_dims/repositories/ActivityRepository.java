package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Activity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends DataTablesRepository<Activity, Integer> {

    boolean existsByName(String name);

    Optional<Activity> findByName(String name);

    @Query("SELECT e.name FROM Activity e WHERE e.isDelete = false ORDER BY e.name ASC")
    List<String> findAllNotDeleted();

    Optional<Activity> findByNameAndIdNot(String name, int id);

    @Query("select count(a) from Activity a where a.isDelete = false")
    int activityCount();
}
