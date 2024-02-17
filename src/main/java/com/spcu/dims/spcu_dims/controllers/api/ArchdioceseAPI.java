package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Archdiocese;

import com.spcu.dims.spcu_dims.services.ArchdioceseServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/archdiocese")
@AllArgsConstructor
public class ArchdioceseAPI {

    private final ArchdioceseServices archdioceseServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> archdioceseCreate(@RequestParam("name") String name, @RequestParam ("diocese")String diocese) {
        Archdiocese archdiocese = archdioceseServices.createArchdiocese(name, diocese);
        if (archdiocese!= null) {
            return new ResponseEntity<>(archdiocese, HttpStatus.CREATED);
        }
        String errorMessage = "Diocese with the name '" + name + "' already exists.";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<Archdiocese>> archdioceseShowAll() {
        return new ResponseEntity<>(archdioceseServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseServices.findById(id);
        if(archdiocese != null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseServices.delete(id);
        if(archdiocese!= null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("diocese") String diocese) {
        Archdiocese archdiocese = archdioceseServices.update(id, name, diocese);
        if(archdiocese!= null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Archdiocese> archdioceses(@Valid @RequestBody DataTablesInput input){
        return archdioceseServices.tableArchdiocese(input);

    }

}
