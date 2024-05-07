package com.spcu.dims.spcu_dims.repositories;


import com.spcu.dims.spcu_dims.views.CountView;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountViewRepository extends DataTablesRepository<CountView, Integer> {

    @Query(value = "SELECT * FROM `spcu-dims`.counts_view;", nativeQuery = true)
    CountView getCount();

}
