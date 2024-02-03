package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Archdiocese;

import com.spcu.dims.spcu_dims.services.ArchdioceseServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/archdiocese")
@AllArgsConstructor
public class ArchdioceseAPI {

    private final ArchdioceseServices archdioceseServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Archdiocese> archdioceseCreate(@RequestParam("name") String name, @RequestParam ("dioceseId")int dioceseId) {
        return new ResponseEntity<>(archdioceseServices.createArchdiocese(name, dioceseId), HttpStatus.CREATED);
    }

    @GetMapping("/show/all")
    public ResponseEntity<List<Archdiocese>> archdioceseShowAll() {
        return new ResponseEntity<>(archdioceseServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseServices.findById(id);
        if(archdiocese != null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Archdiocese archdiocese = archdioceseServices.delete(id);
        if(archdiocese!= null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestParam ("name") String name) {
        Archdiocese archdiocese = archdioceseServices.update(id, name);
        if(archdiocese!= null){
            return new ResponseEntity<>(archdiocese, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

}
