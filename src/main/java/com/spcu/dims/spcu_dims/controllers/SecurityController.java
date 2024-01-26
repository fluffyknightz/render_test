package com.spcu.dims.spcu_dims.controllers;

import com.spcu.dims.spcu_dims.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1")
@RequiredArgsConstructor
public class SecurityController {

    @PostMapping("/register")
    public HttpStatus register(@RequestBody UserDto userDto){

        return HttpStatus.valueOf(HttpStatus.CREATED.value());
    }

}

