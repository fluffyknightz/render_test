package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.dto.BeneficiaryListDto;
import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
import com.spcu.dims.spcu_dims.services.BeneficiaryListServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spcu-dims/api/v1/beneficiary-lists")
@AllArgsConstructor
public class BeneficiaryListAPI {

    private final BeneficiaryListServices beneficiaryListServices;
    private static final String NOT_FOUND = "ID Not Found.";




    @PostMapping("/create")
    public ResponseEntity<Object> beneficiaryListCreate(@RequestBody BeneficiaryListDto reqData){
        Object beneficiaryList = beneficiaryListServices.createBeneficiaryList(reqData);
        if (beneficiaryList instanceof String) {
            return new ResponseEntity<>(beneficiaryList, HttpStatus.CONFLICT);
        } else if (beneficiaryList != null) {
            return new ResponseEntity<>(beneficiaryList, HttpStatus.CREATED);
        } else {
            String errorMessage = "Failed to create Beneficiary";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<BeneficiaryList> beneficiaryList(@Valid @RequestBody DataTablesInput input){
        return beneficiaryListServices.tableBeneficiaryList(input);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        BeneficiaryList beneficiaryList = beneficiaryListServices.findById(id);
        if(beneficiaryList != null){
            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        BeneficiaryList beneficiaryList = beneficiaryListServices.delete(id);
        if (beneficiaryList != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestBody BeneficiaryListDto reqData) {
        Object beneficiaryList = beneficiaryListServices.update(reqData);
        if(beneficiaryList!= null){
            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
