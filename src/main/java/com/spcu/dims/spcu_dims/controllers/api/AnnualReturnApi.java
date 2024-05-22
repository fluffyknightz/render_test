package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.config.MyUserDetail;
import com.spcu.dims.spcu_dims.controllers.CreatedDateSpecification;
import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import com.spcu.dims.spcu_dims.services.AnnualReturnService;
import com.spcu.dims.spcu_dims.views.AnnualReturnView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/spcu-dims/api/v1/annual-return")
@RequiredArgsConstructor
public class AnnualReturnApi {

    private final AnnualReturnService annualReturnService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody AnnualReturnDTO annualReturnDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();
        Object annualReturn = annualReturnService.createAnnualReturn(annualReturnDTO, myUserDetail.getUserId());

        if (annualReturn instanceof String) {
            // Handle the case where activity is a String
            return new ResponseEntity<>(annualReturn, HttpStatus.CONFLICT);
        } else if (annualReturn != null) {
            // Continue with the original logic when activity is not a String
            return new ResponseEntity<>(annualReturn, HttpStatus.CREATED);
        } else {
            // Handle the case where activity is null
            String errorMessage = "Failed to create Annual Return.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        AnnualReturn annualReturn = annualReturnService.delete(id);
        if (annualReturn != null) {
            return new ResponseEntity<>(annualReturn, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody AnnualReturnDTO annualReturnDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        Object annual = annualReturnService.update(annualReturnDTO);

        if (annual instanceof String) {
            // Handle the case where activity is a String
            return new ResponseEntity<>(annual, HttpStatus.CONFLICT);
        } else if (annual != null) {
            // Continue with the original logic when activity is not a String
            return new ResponseEntity<>(annual, HttpStatus.CREATED);
        } else {
            // Handle the case where activity is null
            String errorMessage = "Failed to Update Annual Return";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/show/table")
    public DataTablesOutput<AnnualReturnView> annualReturns(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {

        CreatedDateSpecification<AnnualReturnView> createdDateSpecification = new CreatedDateSpecification<>(input, "createdDate");

        input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("year", "parish"));
        return annualReturnService.annualReturnTable(input, createdDateSpecification);

    }


}
