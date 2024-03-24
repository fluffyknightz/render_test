package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.config.MyUserDetails;
import com.spcu.dims.spcu_dims.config.MyUserDetailsServices;
import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spcu-dims/api/v1/user")
@RequiredArgsConstructor
public class UserAPI {


    private final UserServices userServices;
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsServices myUserDetailsServices;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> userCreate(@RequestParam("username") String username, @RequestParam("role") String role, @RequestParam("password") String password) {

        Object user = userServices.createUser(username, role, password);

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public DataTablesOutput<User> users(@Valid @RequestBody DataTablesInput input) {
        return userServices.tableUser(input);

    }

    @PatchMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        User user = userServices.deleteUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("role") String role, @RequestParam(value = "password", required = false) String password) {

        Object user = userServices.update(id, username, role, password);
        if (user instanceof String) {

            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        } else if (user != null) {
            User newUser = (User) user;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            if (myUserDetails.getUserId() == id) {
                MyUserDetails updatedUserDetails = myUserDetailsServices.loadUserByUsername(newUser.getUsername());
                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            }
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }

        String errorMessage = "Failed to Update User!";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PatchMapping("/password")
    public HttpStatus password(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        if(passwordEncoder.matches(oldPassword, myUserDetails.getPassword())) {
            User user = userServices.passwordChange(newPassword, myUserDetails.getUsername());
            if (user != null) {
                MyUserDetails updatedUserDetails = myUserDetailsServices.loadUserByUsername(user.getUsername());
                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);
                return HttpStatus.OK;
            }
        }


        return HttpStatus.NOT_MODIFIED;
    }

}


