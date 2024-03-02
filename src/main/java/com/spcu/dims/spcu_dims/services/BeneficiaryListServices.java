package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface BeneficiaryListServices {

    Object createBeneficiaryList(String activity, String beneficiary, String disability, String parish, String vulnerability);

    List<String> showAll();

    BeneficiaryList findById(int id);

    BeneficiaryList delete(int id);

    Object update(int id, String activity, String beneficiary, String disability, String parish, String vulnerability);

    DataTablesOutput<BeneficiaryList> tableBeneficiaryList(DataTablesInput input);

}
