package com.spcu.dims.spcu_dims.controllers;


import com.spcu.dims.spcu_dims.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class PageControllers {

    private final RoleRepository roleRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the user's authorities
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // Check if any of the user's authorities match any role in the UserRole Enum
            boolean hasUserRole = authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(roleRepository::existsByName);

            if (hasUserRole) {
                return "redirect:/dashboard";
            }
        }

        // User is not authenticated or has different authorities, show the login page
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/diocese")
    public String diocese() {
        return "diocese";
    }

    @GetMapping("/region")
    public String region() {
        return "region";
    }

    @GetMapping("/archdiocese")
    public String archdiocese() {
        return "archdiocese";
    }

    @GetMapping("/zone")
    public String zone(){
        return "zone";
    }

    @GetMapping("/district")
    public String district(){
        return "district";
    }

    @GetMapping("/township")
    public String township(){
        return "township";
    }

    @GetMapping("/parish")
    public String parish(){
        return "parish";
    }

    @GetMapping("/activity")
    public String activity(){
        return "activity";
    }

    @GetMapping("/beneficiary-list")
    public String beneficiaries(){
        return "beneficiary-list";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
