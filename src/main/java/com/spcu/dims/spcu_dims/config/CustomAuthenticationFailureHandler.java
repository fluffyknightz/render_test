package com.spcu.dims.spcu_dims.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;


public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        if (exception instanceof DisabledException) {

            response.sendRedirect(request.getContextPath() + "/login?error=disabled");
        } else {

            response.sendRedirect(request.getContextPath() + "/login?error=bad_credentials");
        }
    }
}