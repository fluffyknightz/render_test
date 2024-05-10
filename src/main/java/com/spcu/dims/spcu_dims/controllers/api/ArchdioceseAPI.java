package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Archdiocese;
import com.spcu.dims.spcu_dims.services.ArchdioceseService;
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

    private static final String NOT_FOUND = "ID Not Found.";
    private final ArchdioceseService archdioceseService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam("name") String name) {
        if (name.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        Object archdiocese = archdioceseService.createArchdiocese(name);

        if (archdiocese instanceof String) {
            // Handle the case where archdiocese is a String
            return new ResponseEntity<>(archdiocese, HttpStatus.CONFLICT);
        } else if (archdiocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(archdiocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to create Archdiocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {

        return new ResponseEntity<>(archdioceseService.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseService.findById(id);
        if (archdiocese != null) {
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseService.delete(id);
        if (archdiocese != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name) {
        if (name.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        Object archdiocese = archdioceseService.update(id, name);

        if (archdiocese instanceof String) {
            // Handle the case where archdiocese is a String
            return new ResponseEntity<>(archdiocese, HttpStatus.CONFLICT);
        } else if (archdiocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(archdiocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Archdiocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Archdiocese> archdioceses(@Valid @RequestBody DataTablesInput input) {

        return archdioceseService.tableArchdiocese(input);

    }

}
