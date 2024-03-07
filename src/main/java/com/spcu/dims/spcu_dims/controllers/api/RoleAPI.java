package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.services.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/roles")
@RequiredArgsConstructor
public class RoleAPI {

    private final RoleServices roleServices;

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {
        return new ResponseEntity<>(roleServices.findAllName(), HttpStatus.OK);
    }

}
