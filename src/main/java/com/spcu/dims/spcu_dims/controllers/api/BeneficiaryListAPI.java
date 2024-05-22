package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.config.MyUserDetail;
import com.spcu.dims.spcu_dims.controllers.CreatedDateSpecification;
import com.spcu.dims.spcu_dims.dto.BeneficiaryListDto;
import com.spcu.dims.spcu_dims.entities.beneficiary_list.BeneficiaryList;
import com.spcu.dims.spcu_dims.views.BeneficiaryListView;
import com.spcu.dims.spcu_dims.services.BeneficiaryListService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/spcu-dims/api/v1/beneficiary-lists")
@AllArgsConstructor
public class BeneficiaryListAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final BeneficiaryListService beneficiaryListService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody BeneficiaryListDto reqData, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        MyUserDetail myUserDetail = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object beneficiaryList = beneficiaryListService.createBeneficiaryList(reqData, myUserDetail.getUserId());
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
    public DataTablesOutput<BeneficiaryListView> beneficiaryLists(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {
        CreatedDateSpecification<BeneficiaryListView> createdDateSpecification = new CreatedDateSpecification<>(input, "createdDate");
        input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("activityName", "parishName"));

        return beneficiaryListService.tableBeneficiaryList(input, createdDateSpecification);
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        BeneficiaryList beneficiaryList = beneficiaryListService.findById(id);
        if (beneficiaryList != null) {
            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        BeneficiaryList beneficiaryList = beneficiaryListService.delete(id);
        if (beneficiaryList != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody BeneficiaryListDto reqData, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        Object beneficiaryList = beneficiaryListService.update(reqData);
        if (beneficiaryList != null) {
            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }


}
