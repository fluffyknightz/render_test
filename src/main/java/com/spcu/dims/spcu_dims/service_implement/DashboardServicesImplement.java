package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.CountView;
import com.spcu.dims.spcu_dims.repositories.CountViewRepository;
import com.spcu.dims.spcu_dims.services.DashboardServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServicesImplement implements DashboardServices {

    private final CountViewRepository countViewRepository;


    @Override
    public CountView count() {
        return countViewRepository.getCount();
    }
}
