package com.spcu.dims.spcu_dims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String Dashboard(){
        return "dashboard";
    }

    @GetMapping("/home")
    public String Home(){
        return "home";
    }
}