package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.services.TownshipServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/township")
@AllArgsConstructor
public class TownshipAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final TownshipServices townshipServices;

    @PostMapping("/create")
    public ResponseEntity<Object> townshipCreate(@RequestParam("name") String name, @RequestParam("district") String district, @RequestParam("zone") String zone) {
        Object township = townshipServices.createTownship(name, district, zone);
        if (township instanceof String) {

            return new ResponseEntity<>(township, HttpStatus.CONFLICT);
        } else if (township != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(township, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Create Township with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> townshipShowAll() {
        return new ResponseEntity<>(townshipServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Township township = townshipServices.findById(id);
        if (township != null) {
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Township township = townshipServices.delete(id);
        if (township != null) {
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("district") String district, @RequestParam("zone") String zone) {
        Object township = townshipServices.update(id, name, district, zone);
        if (township instanceof String) {

            return new ResponseEntity<>(township, HttpStatus.CONFLICT);
        } else if (township != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(township, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Township with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Township> townships(@Valid @RequestBody DataTablesInput input) {
        return townshipServices.tableTownship(input);

    }

}
