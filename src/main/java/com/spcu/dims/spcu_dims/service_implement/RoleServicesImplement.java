package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.repositories.RoleRepository;
import com.spcu.dims.spcu_dims.services.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServicesImplement implements RoleServices {

    private final RoleRepository roleRepository;
    @Override
    public List<String> findAllName() {
        return roleRepository.findAllNotDeleted();
    }
}
