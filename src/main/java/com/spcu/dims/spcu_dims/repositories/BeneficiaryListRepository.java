package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryListRepository extends JpaRepository<BeneficiaryList, Integer> {
}
