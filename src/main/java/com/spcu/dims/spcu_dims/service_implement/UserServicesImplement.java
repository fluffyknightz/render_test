package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.repositories.UserRepository;
import com.spcu.dims.spcu_dims.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServicesImplement implements UserServices {

    private final UserRepository userRepository;


    @Override
    public Optional<User> findByUsernameCaseSensitive(String username) {
        return userRepository.findByUsernameCaseSensitive(username);
        }
    }

