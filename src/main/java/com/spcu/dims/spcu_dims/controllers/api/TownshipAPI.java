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

    private final TownshipServices townshipServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> townshipCreate(@RequestParam("name") String name, @RequestParam ("district") String district) {
        Township township = townshipServices.createTownship(name, district);
        if (township!= null) {
            return new ResponseEntity<>(township, HttpStatus.CREATED);
        }
        String errorMessage = "District with the name '" + name + "' already exists.";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<Township>> townshipShowAll() {
        return new ResponseEntity<>(townshipServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Township township = townshipServices.findById(id);
        if(township != null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Township township = townshipServices.delete(id);
        if(township!= null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("district") String district) {
        Township township = townshipServices.update(id, name, district);
        if(township!= null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Township> townships(@Valid @RequestBody DataTablesInput input){
        return townshipServices.tableTownship(input);

    }

}
