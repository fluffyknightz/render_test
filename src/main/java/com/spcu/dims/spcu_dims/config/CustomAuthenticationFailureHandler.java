package com.spcu.dims.spcu_dims.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


import java.io.IOException;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof DisabledException) {
            // Log the disabled exception
            System.out.println("DisabledException caught: " + exception.getMessage());
            response.sendRedirect(request.getContextPath() + "/login?error=disabled");
        } else {
            // Log other exceptions
            System.out.println("Other exception caught: " + exception.getMessage());
            response.sendRedirect(request.getContextPath() + "/login?error=bad_credentials");
        }
    }
}