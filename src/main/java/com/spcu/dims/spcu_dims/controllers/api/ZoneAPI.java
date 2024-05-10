package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.services.ZoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/spcu-dims/api/v1/zone")
@AllArgsConstructor
public class ZoneAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final ZoneService zoneService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam("name") String name, @RequestParam("diocese") String diocese) {

        if (name.isEmpty() || diocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }

        Object zone = zoneService.createZone(name, diocese);
        if (zone instanceof String) {

            return new ResponseEntity<>(zone, HttpStatus.CONFLICT);
        } else if (zone != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(zone, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Create Zone with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Zone> zones(@Valid @RequestBody DataTablesInput input) {
        return zoneService.tableZone(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Zone zone = zoneService.delete(id);
        if (zone != null) {
            return new ResponseEntity<>(zone, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("diocese") String diocese) {

        if (name.isEmpty() || diocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }

        Object zone = zoneService.update(id, name, diocese);
        if (zone instanceof String) {

            return new ResponseEntity<>(zone, HttpStatus.CONFLICT);
        } else if (zone != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(zone, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Zone with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {
        return new ResponseEntity<>(zoneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Zone zone = zoneService.findById(id);
        if (zone != null) {
            return new ResponseEntity<>(zone, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show")
    public ResponseEntity<Object> findByDiocese(@RequestParam("diocese") String diocese) {
        if (diocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        List<String> zoneName = zoneService.findByDioceseName(diocese);
        if (zoneName != null) {
            return new ResponseEntity<>(zoneName, HttpStatus.OK);
        }
        String errorMessage = "Zone that related to Diocese name " + diocese + " does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
