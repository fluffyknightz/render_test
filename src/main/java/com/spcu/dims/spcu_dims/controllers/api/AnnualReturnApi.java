package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import com.spcu.dims.spcu_dims.entities.annual_return.AnnualReturn;
import com.spcu.dims.spcu_dims.services.AnnualReturnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/spcu-dims/api/v1/annual-return")
@RequiredArgsConstructor
public class AnnualReturnApi {

    private final AnnualReturnService annualReturnService;

    @PostMapping("/create")
    public String create(@Valid @RequestBody AnnualReturnDTO annualReturnDTO, BindingResult result) {
        if (result.hasErrors()) {
            return result.getAllErrors().toString();
        }
        return "Annual Return Created";
    }

    @PostMapping("/show/table")
    public DataTablesOutput<AnnualReturn> annualReturns(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {
        input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("parish", "year"));
        return annualReturnService.annualReturnTable(input);

    }


}
