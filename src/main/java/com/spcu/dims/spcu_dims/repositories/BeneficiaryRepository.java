package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.beneficiary_list.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
}
