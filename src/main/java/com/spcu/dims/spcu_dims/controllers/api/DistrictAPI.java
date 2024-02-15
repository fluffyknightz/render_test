package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.District;
import com.spcu.dims.spcu_dims.services.DistrictServices;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<District> createDistrict(@RequestParam("name") String name, @RequestParam("regionId") int regionId) {
        return new ResponseEntity<>(districtServices.createDistrict(name, regionId), HttpStatus.CREATED);
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<District>> showAll() {

        return new ResponseEntity<>(districtServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        District district = districtServices.findById(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        District district = districtServices.delete(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestParam("name") String name) {
        District district = districtServices.update(id, name);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
