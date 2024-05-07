package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.services.RegionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/region")
@AllArgsConstructor
public class RegionAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<Object> createRegion(@RequestParam("name") String name) {

        Object region = regionService.createRegion(name);
        if (region instanceof String) {

            return new ResponseEntity<>(region, HttpStatus.CONFLICT);
        } else if (region != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(region, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Create Region with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {

        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Region region = regionService.findById(id);
        if (region != null) {
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Region region = regionService.delete(id);
        if (region != null) {
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name) {
        Object region = regionService.update(id, name);

        if (region instanceof String) {
            return new ResponseEntity<>(region, HttpStatus.CONFLICT);
        } else if (region != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(region, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Region with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Region> regions(@Valid @RequestBody DataTablesInput input) {
        return regionService.tableRegion(input);

    }
}
