package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import com.spcu.dims.spcu_dims.services.BeneficiaryListServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1/beneficiaries-list")
@AllArgsConstructor
public class BeneficiariesListAPI {

    private final BeneficiaryListServices beneficiaryListServices;

    @PostMapping("/show/table")
    public DataTablesOutput<BeneficiaryList> beneficiariesList(@Valid @RequestBody DataTablesInput input){
        return beneficiaryListServices.tableBeneficiaryList(input);
    }
}
