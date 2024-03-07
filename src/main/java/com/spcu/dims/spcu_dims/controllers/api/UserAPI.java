package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spcu-dims/api/v1/user")
@RequiredArgsConstructor
public class UserAPI {


    private final UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<Object> userCreate(@RequestParam("username") String username, @RequestParam("role") String role) {

        Object user = userServices.createUser(username, role);

        if (user instanceof String) {
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        } else if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            String errorMessage = "Failed to create User with the name '" + username + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/show/table")
    public DataTablesOutput<User> users(@Valid @RequestBody DataTablesInput input) {
        return userServices.tableUser(input);

    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        User user = userServices.deleteUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("role") String role){

    Object user = userServices.update(id, username, role);
        if (user instanceof String) {

            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        } else if (user != null) {

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {

            String errorMessage = "Failed to Update Activity with the name '" + username + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}
