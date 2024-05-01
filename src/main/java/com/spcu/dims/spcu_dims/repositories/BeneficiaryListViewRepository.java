package com.spcu.dims.spcu_dims.repositories;


import com.spcu.dims.spcu_dims.entities.BeneficiaryListView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface BeneficiaryListViewRepository extends DataTablesRepository<BeneficiaryListView, Integer> {


}
