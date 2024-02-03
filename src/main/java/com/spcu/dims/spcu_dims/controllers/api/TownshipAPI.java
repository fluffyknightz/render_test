package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Township;
import com.spcu.dims.spcu_dims.services.DistrictServices;
import com.spcu.dims.spcu_dims.services.TownshipServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/township")
@AllArgsConstructor
public class TownshipAPI {

    private  final TownshipServices townshipServices;
    private static final String NOT_FOUND = "ID Not Found.";
    @PostMapping("/create")
    public ResponseEntity<Township> createTownship(@RequestParam ("name") String name, @RequestParam ("districtId") int districtId) {
        return new ResponseEntity<>(townshipServices.createTownship(name, districtId), HttpStatus.CREATED);
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<Township>> showAll() {

        return new ResponseEntity<>(townshipServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Township township = townshipServices.findById(id);
        if(township != null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Township township = townshipServices.delete(id);
        if(township!= null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestParam("name") String name){
        Township township = townshipServices.update(id, name);
        if(township!= null){
            return new ResponseEntity<>(township, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

}
