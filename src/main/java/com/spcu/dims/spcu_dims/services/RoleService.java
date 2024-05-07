package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<String> findAllName();

    Optional<Role> findByName(String name);

}
