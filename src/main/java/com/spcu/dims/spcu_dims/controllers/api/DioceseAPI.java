package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Diocese;

import com.spcu.dims.spcu_dims.services.DioceseServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/diocese")
@AllArgsConstructor
public class DioceseAPI {

    private final DioceseServices dioceseServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> archdioceseCreate(@RequestParam("name") String name, @RequestParam ("archdiocese")String archdiocese) {
        Object diocese = dioceseServices.dioceseCreate(name,archdiocese);
        if (diocese instanceof String) {

            return new ResponseEntity<>(diocese, HttpStatus.CONFLICT);
        } else if (diocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(diocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to create Diocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> archdioceseShowAll() {

        return new ResponseEntity<>(dioceseServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Diocese diocese = dioceseServices.findById(id);
        if(diocese != null){
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Diocese diocese = dioceseServices.delete(id);
        if(diocese!= null){
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("archdiocese") String archdiocese) {
        Object diocese = dioceseServices.update(id, name, archdiocese);
        if (diocese instanceof String) {
            return new ResponseEntity<>(diocese, HttpStatus.CONFLICT);
        } else if (diocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(diocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Diocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Diocese> archdioceses(@Valid @RequestBody DataTablesInput input){
        return dioceseServices.tableDiocese(input);

    }

    @PostMapping("/show")
    public ResponseEntity<Object> create(@RequestParam ("archdiocese") String archdiocese) {
        List<String> dioceseName = dioceseServices.findByArchdioceseName(archdiocese);
        if(dioceseName!= null){
            return new ResponseEntity<>(dioceseName, HttpStatus.OK);
        }
        String errorMessage = "Diocese that related to Archdiocese name " + archdiocese + " does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
