package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.entities.CountView;
import com.spcu.dims.spcu_dims.services.DashboardServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1/dashboard")
@AllArgsConstructor
public class DashboardAPI {

    private final DashboardServices dashboardServices;


    @PostMapping("/count")
    public ResponseEntity<CountView> count() {
        return new ResponseEntity<>(dashboardServices.count(), HttpStatus.OK);
    }
}
