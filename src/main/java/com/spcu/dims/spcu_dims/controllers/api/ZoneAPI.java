package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Zone;
import com.spcu.dims.spcu_dims.services.ZoneServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/spcu-dims/api/v1/zone")
@AllArgsConstructor
public class ZoneAPI {

    private final ZoneServices zoneServices;
    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/show/table")
    public DataTablesOutput<Zone> zones(@Valid @RequestBody DataTablesInput input){
        return zoneServices.tableZone(input);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Zone zone = zoneServices.delete(id);
        if (zone != null) {
            return new ResponseEntity<>(zone, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
