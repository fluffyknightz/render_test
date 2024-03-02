package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import com.spcu.dims.spcu_dims.repositories.BeneficiaryListRepository;
import com.spcu.dims.spcu_dims.services.BeneficiaryListServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryListServicesImplement implements BeneficiaryListServices {

    private final BeneficiaryListRepository beneficiaryListRepository;

    @Override
    public Object createBeneficiaryList(String activity, String beneficiary, String disability, String parish, String vulnerability) {
        return null;
    }

    @Override
    public List<String> showAll() {
        return null;
    }

    @Override
    public BeneficiaryList findById(int id) {
        return null;
    }

    @Override
    public BeneficiaryList delete(int id) {
        return null;
    }

    @Override
    public Object update(int id, String activity, String beneficiary, String disability, String parish, String vulnerability) {
        return null;
    }

    @Override
    public DataTablesOutput<BeneficiaryList> tableBeneficiaryList(DataTablesInput input) {
        return beneficiaryListRepository.findAll(input);
    }
}
