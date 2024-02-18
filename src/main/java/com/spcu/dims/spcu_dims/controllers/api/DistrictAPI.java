package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.services.DistrictServices;
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

    private final DistrictServices districtServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> districtCreate(@RequestParam("name") String name, @RequestParam ("region")String region) {
        District district = districtServices.createDistrict(name, region);
        if (district!= null) {
            return new ResponseEntity<>(district, HttpStatus.CREATED);
        }
        System.out.println("reachjjjj");
        String errorMessage = "District with the name '" + name + "' already exists.";
        System.out.println(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @PostMapping("/show/table")
    public DataTablesOutput<District> districts(@Valid @RequestBody DataTablesInput input){
        return districtServices.tableDistrict(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        District district = districtServices.delete(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("region") String region) {
        District district = districtServices.update(id, name, region);
        if(district!= null){
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<District>> zoneShowAll() {
        return new ResponseEntity<>(districtServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        District district = districtServices.findById(id);
        if(district != null){
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/show")
    public ResponseEntity<Object> create(@RequestParam ("region") String region) {
        List<District> districts = districtServices.findByRegionName(region);
        if(districts!= null){
            return new ResponseEntity<>(districts, HttpStatus.OK);
        }
        String errorMessage = "District that related to Region name " + region + " dont exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
