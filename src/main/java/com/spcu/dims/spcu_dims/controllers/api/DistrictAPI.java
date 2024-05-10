package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.services.DistrictService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/district")
@AllArgsConstructor
public class DistrictAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<Object> districtCreate(@RequestParam("name") String name, @RequestParam("region") String region) {

        if (name.isEmpty() || region.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        
        Object district = districtService.createDistrict(name, region);
        if (district instanceof String) {

            return new ResponseEntity<>(district, HttpStatus.CONFLICT);
        } else if (district != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(district, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to create District with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<District> districts(@Valid @RequestBody DataTablesInput input) {
        return districtService.tableDistrict(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        District district = districtService.delete(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("region") String region) {
        if (name.isEmpty() || region.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        
        Object district = districtService.update(id, name, region);
        if (district instanceof String) {

            return new ResponseEntity<>(district, HttpStatus.CONFLICT);
        } else if (district != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(district, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update District with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {
        return new ResponseEntity<>(districtService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        District district = districtService.findById(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show")
    public ResponseEntity<Object> findByRegion(@RequestParam("region") String region) {

        if (region.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        List<String> districts = districtService.findByRegionName(region);
        if (districts != null) {
            return new ResponseEntity<>(districts, HttpStatus.OK);
        }
        String errorMessage = "District that related to Region name " + region + " does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
