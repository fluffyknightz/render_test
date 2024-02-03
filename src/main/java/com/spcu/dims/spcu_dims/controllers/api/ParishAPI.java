package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Parish;

import com.spcu.dims.spcu_dims.services.ParishServices;

import lombok.AllArgsConstructor;
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
    public ResponseEntity<Parish> createParish(@RequestParam("name") String name, @RequestParam("archdioceseId") int archdioceseId) {
        return new ResponseEntity<>(parishServices.createParish(name, archdioceseId), HttpStatus.CREATED);
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<Parish>> showAll() {

        return new ResponseEntity<>(parishServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Parish parish = parishServices.findById(id);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Parish parish = parishServices.delete(id);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestParam("name") String name) {
        Parish parish = parishServices.update(id, name);
        if (parish != null) {
            return new ResponseEntity<>(parish, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
