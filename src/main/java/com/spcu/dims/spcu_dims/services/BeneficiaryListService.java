package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.controllers.CreatedDateSpecification;
import com.spcu.dims.spcu_dims.dto.BeneficiaryListDto;
import com.spcu.dims.spcu_dims.entities.beneficiary_list.BeneficiaryList;
import com.spcu.dims.spcu_dims.views.BeneficiaryListView;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface BeneficiaryListService {

    Object createBeneficiaryList(BeneficiaryListDto reqData, int userId);

    List<BeneficiaryList> showAll();

    BeneficiaryList findById(int id);

    BeneficiaryList delete(int id);

    Object update(BeneficiaryListDto reqData);

    DataTablesOutput<BeneficiaryListView> tableBeneficiaryList(DataTablesInput input, CreatedDateSpecification createdDateSpecification);

}
