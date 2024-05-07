package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.beneficiary_list.Disability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisabilityRepository extends JpaRepository<Disability, Integer> {
}
