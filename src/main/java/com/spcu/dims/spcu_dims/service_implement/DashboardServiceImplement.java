package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.views.CountView;
import com.spcu.dims.spcu_dims.repositories.CountViewRepository;
import com.spcu.dims.spcu_dims.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImplement implements DashboardService {

    private final CountViewRepository countViewRepository;


    @Override
    public CountView count() {
        return countViewRepository.getCount();
    }
}
