package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Region;
import com.spcu.dims.spcu_dims.services.RegionServices;
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

    private final RegionServices regionServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> createRegion(@RequestParam ("name") String name) {

        Region region = regionServices.createRegion(name);
        if (region!= null) {
            return new ResponseEntity<>(region, HttpStatus.CREATED);
        }
        String errorMessage = "Region with the name '" + name + "' already exists.";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<Region>> showAll() {

        return new ResponseEntity<>(regionServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Region region = regionServices.findById(id);
        if(region != null){
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Region region = regionServices.delete(id);
        if(region!= null){
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name){
        Region region = regionServices.update(id, name);
        if(region!= null){
            return new ResponseEntity<>(region, HttpStatus.OK);
        }
        return new ResponseEntity<>("Region with the name '" + name + "' already exists.\"", HttpStatus.CONFLICT);
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Region> regions(@Valid @RequestBody DataTablesInput input){
        return regionServices.tableRegion(input);

    }
}
