package com.spcu.dims.spcu_dims.controllers.api;


import com.spcu.dims.spcu_dims.entities.Congregation;
import com.spcu.dims.spcu_dims.services.CongregationServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/congregation")
@RequiredArgsConstructor
public class CongregationApi {

    private static final String NOT_FOUND = "ID Not Found.";
    private final CongregationServices congregationServices;

    @PostMapping("/create")
    public ResponseEntity<Object> dioceseCreate(@RequestParam("name") String name) {

        Object congregation = congregationServices.createCongregation(name);

        if (congregation instanceof String) {
            // Handle the case where congregation is a String
            return new ResponseEntity<>(congregation, HttpStatus.CONFLICT);
        } else if (congregation != null) {
            // Continue with the original logic when congregation is not a String
            return new ResponseEntity<>(congregation, HttpStatus.CREATED);
        } else {
            // Handle the case where congregation is null
            String errorMessage = "Failed to create Congregation with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {

        return new ResponseEntity<>(congregationServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Congregation congregation = congregationServices.findById(id);
        if (congregation != null) {
            return new ResponseEntity<>(congregation, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Congregation congregation = congregationServices.delete(id);
        if (congregation != null) {
            return new ResponseEntity<>(congregation, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name) {
        Object congregation = congregationServices.update(id, name);

        if (congregation instanceof String) {
            // Handle the case where congregation is a String
            return new ResponseEntity<>(congregation, HttpStatus.CONFLICT);
        } else if (congregation != null) {
            // Continue with the original logic when congregation is not a String
            return new ResponseEntity<>(congregation, HttpStatus.CREATED);
        } else {
            // Handle the case where congregation is null
            String errorMessage = "Failed to Update Congregation with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Congregation> dioceses(@Valid @RequestBody DataTablesInput input) {
        return congregationServices.congregationTable(input);

    }

}
