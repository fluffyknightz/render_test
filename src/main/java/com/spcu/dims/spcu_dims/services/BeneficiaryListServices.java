package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.dto.BeneficiaryListDto;
import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface BeneficiaryListServices {

    Object createBeneficiaryList(BeneficiaryListDto reqData);

    List<String> showAll();

    BeneficiaryList findById(int id);

    BeneficiaryList delete(int id);

    Object update(BeneficiaryListDto reqData);

    DataTablesOutput<BeneficiaryList> tableBeneficiaryList(DataTablesInput input);

}
