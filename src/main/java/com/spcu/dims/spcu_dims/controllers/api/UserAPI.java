package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1/user")
@RequiredArgsConstructor
public class UserAPI {


    private final UserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<Object> userCreate(@RequestParam("name") String name, @RequestParam("role")String role) {

        Object user = userServices.createUser(name, role);

        if (user instanceof String) {
            // Handle the case where activity is a String
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        } else if (user != null) {
            // Continue with the original logic when activity is not a String
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            // Handle the case where activity is null
            String errorMessage = "Failed to create User with the name '" + name + "'.";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
