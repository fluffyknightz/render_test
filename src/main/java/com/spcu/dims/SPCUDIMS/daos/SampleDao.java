package com.spcu.dims.SPCUDIMS.daos;

import com.spcu.dims.SPCUDIMS.models.SampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDao extends JpaRepository<SampleModel,Integer> {
}
