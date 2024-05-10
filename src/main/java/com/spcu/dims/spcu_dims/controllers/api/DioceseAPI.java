package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Diocese;
import com.spcu.dims.spcu_dims.services.DioceseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/diocese")
@AllArgsConstructor
public class DioceseAPI {

    private static final String NOT_FOUND = "ID Not Found.";
    private final DioceseService dioceseService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam("name") String name, @RequestParam("archdiocese") String archdiocese) {

        if (name.isEmpty() || archdiocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        
        Object diocese = dioceseService.dioceseCreate(name, archdiocese);
        if (diocese instanceof String) {

            return new ResponseEntity<>(diocese, HttpStatus.CONFLICT);
        } else if (diocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(diocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to create Diocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {

        return new ResponseEntity<>(dioceseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Diocese diocese = dioceseService.findById(id);
        if (diocese != null) {
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Diocese diocese = dioceseService.delete(id);
        if (diocese != null) {
            return new ResponseEntity<>(diocese, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("archdiocese") String archdiocese) {
        if (name.isEmpty() || archdiocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        Object diocese = dioceseService.update(id, name, archdiocese);
        if (diocese instanceof String) {
            return new ResponseEntity<>(diocese, HttpStatus.CONFLICT);
        } else if (diocese != null) {
            // Continue with the original logic when archdiocese is not a String
            return new ResponseEntity<>(diocese, HttpStatus.CREATED);
        } else {
            // Handle the case where archdiocese is null
            String errorMessage = "Failed to Update Diocese with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Diocese> dioceses(@Valid @RequestBody DataTablesInput input) {
        return dioceseService.tableDiocese(input);

    }

    @PostMapping("/show")
    public ResponseEntity<Object> findByArchdiocese(@RequestParam("archdiocese") String archdiocese) {
        if (archdiocese.isEmpty()) {
            return new ResponseEntity<>("Please Fill All Required Data!", HttpStatus.BAD_REQUEST);
        }
        List<String> dioceseName = dioceseService.findByArchdioceseName(archdiocese);
        if (dioceseName != null) {
            return new ResponseEntity<>(dioceseName, HttpStatus.OK);
        }
        String errorMessage = "Diocese that related to Archdiocese name " + archdiocese + " does not exist.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
