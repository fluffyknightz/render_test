package com.spcu.dims.spcu_dims.service;

import com.spcu.dims.spcu_dims.models.User;

import java.util.Optional;

public interface UserServices {

    Optional<User> findByUsernameCaseSensitive(String username);
}
