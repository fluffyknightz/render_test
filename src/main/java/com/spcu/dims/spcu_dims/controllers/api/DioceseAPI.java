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
    public ResponseEntity<Diocese> dioceseCreate(@RequestParam("name") String name) {
        return new ResponseEntity<>(dioceseServices.dioceseCreate(name), HttpStatus.CREATED);
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<Diocese>> showAll() {

        return new ResponseEntity<>(dioceseServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Diocese diocese = dioceseServices.findById(id);
        if (diocese != null) {
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Diocese diocese = dioceseServices.delete(id);
        if (diocese != null) {
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestParam("name") String name) {
        Diocese diocese = dioceseServices.update(id, name);
        if (diocese != null) {
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Diocese> dioceses(@Valid @RequestBody DataTablesInput input){
        System.out.println(input);
        DataTablesOutput<Diocese> output = dioceseServices.tableDiocese(input);
        System.out.println(output);
        return output;

    }

}
