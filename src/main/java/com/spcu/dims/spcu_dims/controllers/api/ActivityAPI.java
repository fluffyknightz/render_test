package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.Activity;
import com.spcu.dims.spcu_dims.services.ActivityServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/spcu-dims/api/v1/activity")
@RequiredArgsConstructor
public class ActivityAPI {

    private final ActivityServices activityServices;

    private static final String NOT_FOUND = "ID Not Found.";

    @PostMapping("/create")
    public ResponseEntity<Object> dioceseCreate(@RequestParam("name") String name) {

        Object activity = activityServices.createActivity(name);

        if (activity instanceof String) {
            // Handle the case where activity is a String
            return new ResponseEntity<>(activity, HttpStatus.CONFLICT);
        } else if (activity != null) {
            // Continue with the original logic when activity is not a String
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        } else {
            // Handle the case where activity is null
            String errorMessage = "Failed to create Activity with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/all")
    public ResponseEntity<List<String>> showAll() {

        return new ResponseEntity<>(activityServices.showAll(), HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        Activity activity = activityServices.findById(id);
        if (activity != null) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Activity activity = activityServices.delete(id);
        if (activity != null) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("name") String name) {
        Object activity = activityServices.update(id, name);

        if (activity instanceof String) {
            // Handle the case where activity is a String
            return new ResponseEntity<>(activity, HttpStatus.CONFLICT);
        } else if (activity != null) {
            // Continue with the original logic when activity is not a String
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        } else {
            // Handle the case where activity is null
            String errorMessage = "Failed to Update Activity with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<Activity> dioceses(@Valid @RequestBody DataTablesInput input) {
        return activityServices.tableActivity(input);

    }
}
