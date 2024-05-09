package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.beneficiary_list.BeneficiaryList;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;


public interface BeneficiaryListRepository extends DataTablesRepository<BeneficiaryList, Integer> {

}
