package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Parish;
import com.spcu.dims.spcu_dims.services.ParishService;
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

    private static final String NOT_FOUND = "ID Not Found.";
    private final ParishService parishService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam("name") String name, @RequestParam("township") String township, @RequestParam("congregation") String congregation, @RequestParam(value = "note", required = false) String note) {

        if (name.isEmpty() || township.isEmpty() || congregation.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        
        Object parish = parishService.createParish(name, township, congregation, note);
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
    public DataTablesOutput<Parish> parishes(@Valid @RequestBody DataTablesInput input) {
        return parishService.tableParish(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Parish parish = parishService.delete(id);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("township") String township, @RequestParam("congregation") String congregation, @RequestParam(value = "note", required = false) String note) {

        if (name.isEmpty() || township.isEmpty() || congregation.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        
        Object parish = parishService.update(id, name, township, congregation, note);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {
        return new ResponseEntity<>(parishService.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Parish parish = parishService.findById(id);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show")
    public ResponseEntity<Object> findByZone(@RequestParam("zone") String zone) {

        if (zone.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        List<String> parishName = parishService.findByZoneName(zone);
        if (parishName != null) {
            return new ResponseEntity<>(parishName, HttpStatus.OK);
        }
        String errorMessage = "Parish that related to Zone name " + zone + " does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
