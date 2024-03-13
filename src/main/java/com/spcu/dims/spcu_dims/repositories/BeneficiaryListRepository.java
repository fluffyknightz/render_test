package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Activity;
import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import com.spcu.dims.spcu_dims.entities.Parish;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryListRepository extends DataTablesRepository<BeneficiaryList, Integer> {

}
