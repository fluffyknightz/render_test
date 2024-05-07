package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Role;
import com.spcu.dims.spcu_dims.repositories.RoleRepository;
import com.spcu.dims.spcu_dims.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplement implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<String> findAllName() {
        return roleRepository.findAllNotDeleted();
    }

    @Override
    public Optional<Role> findByName(String name) {

        return roleRepository.findByName(name);
    }
}
