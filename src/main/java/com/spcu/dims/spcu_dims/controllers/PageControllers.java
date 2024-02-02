
package com.spcu.dims.spcu_dims.controllers;

import com.spcu.dims.spcu_dims.entities.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.Collection;

@Controller
public class PageControllers {

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Get the user's authorities
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // Check if any of the user's authorities match any role in the UserRole Enum
            boolean hasUserRole = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> Arrays.stream(Role.values())
                            .anyMatch(userRole -> userRole.name().equals(role)));

            if (hasUserRole) {
                return "redirect:/dashboard";
            }
        }

        return "login";

    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/adminPage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPage() {
        return "admin only side";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException() {
        // Redirect to the error page for unauthorized access
        return "error-403";
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public String pageNotFound() {
        // Redirect to the error page for unauthorized access
        return "error-404";
    }






}
