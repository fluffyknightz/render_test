package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.dto.AnnualReturnDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1/annual-return")
@RequiredArgsConstructor
public class AnnualReturnApi {


    @PostMapping("/create")
    public String create(@Valid @RequestBody AnnualReturnDTO annualReturnDTO, BindingResult result) {
    if(result.hasErrors()){
        return result.getAllErrors().toString();
    }
        return "Annual Return Created";
    }

}
