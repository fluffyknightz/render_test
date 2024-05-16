package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.controllers.CreatedDateSpecification;
import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import com.spcu.dims.spcu_dims.repositories.AnnualReturnRepository;
import com.spcu.dims.spcu_dims.repositories.AnnualReturnViewRepository;
import com.spcu.dims.spcu_dims.services.AnnualReturnService;
import com.spcu.dims.spcu_dims.views.AnnualReturnView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnualReturnServiceImplement implements AnnualReturnService {

    private final AnnualReturnRepository annualReturnRepository;
    private final AnnualReturnViewRepository annualReturnViewRepository;

    @Override
    public Object createAnnualReturn(AnnualReturnDTO annualReturnDTO) {
        return null;
    }

    @Override
    public List<String> showAll() {
        return List.of();
    }

    @Override
    public AnnualReturn findById(int id) {
        return null;
    }

    @Override
    public AnnualReturn delete(int id) {
        return null;
    }

    @Override
    public Object update(AnnualReturnDTO annualReturnDTO) {
        return null;
    }

    @Override
    public DataTablesOutput<AnnualReturnView> annualReturnTable(DataTablesInput input, CreatedDateSpecification<AnnualReturnView> createdDateSpecification) {
        return annualReturnViewRepository.findAll(input, createdDateSpecification);
    }
}
