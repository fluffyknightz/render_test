package com.spcu.dims.spcu_dims.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController{

    @GetMapping("/error")
    public Object showErrorPage(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode.equals(HttpStatus.NOT_FOUND.value())) {
                return "error-404";
            } else if (statusCode.equals(HttpStatus.FORBIDDEN.value())) {
                return "error-403";
            } else if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
                return "error-500";
            } else if (statusCode.equals(HttpStatus.BAD_REQUEST.value())) {
                return "error-400";
            } else if (statusCode.equals(HttpStatus.UNAUTHORIZED.value())) {
                return "error-401";
            } else if (statusCode.equals(HttpStatus.BAD_GATEWAY.value())) {
                return "error-502";
            } else if (statusCode.equals(HttpStatus.SERVICE_UNAVAILABLE.value())) {
                return "error-503";
            } else if (statusCode.equals(HttpStatus.GATEWAY_TIMEOUT.value())) {
                return "error-504";
            } else if (statusCode.equals(HttpStatus.METHOD_NOT_ALLOWED.value())) {
                return "error-405";
            }
        }

        return null;
    }
}

