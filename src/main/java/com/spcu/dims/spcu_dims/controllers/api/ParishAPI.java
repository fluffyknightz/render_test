package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.services.ParishServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/parish")
@AllArgsConstructor
public class ParishAPI {

    private final ParishServices parishServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> parishCreate(@RequestParam("name") String name, @RequestParam ("zone")String diocese, @RequestParam ("township")String township) {
        Object parish = parishServices.createParish(name, diocese, township);
        if (parish instanceof String) {

            return new ResponseEntity<>(parish, HttpStatus.CONFLICT);
        } else if (parish != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(parish, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to create Parish with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Parish> parish(@Valid @RequestBody DataTablesInput input){
        return parishServices.tableParish(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Parish parish = parishServices.delete(id);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("zone") String zone, @RequestParam("township") String township) {
        Object parish = parishServices.update(id, name, zone, township);
        if(parish!= null){
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> parishShowAll() {
        return new ResponseEntity<>(parishServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Parish parish = parishServices.findById(id);
        if(parish != null){
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
