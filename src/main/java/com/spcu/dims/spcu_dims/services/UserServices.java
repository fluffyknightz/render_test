package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.User;

import java.util.Optional;

public interface UserServices {

    Optional<User> findByUsernameCaseSensitive(String username);
}
