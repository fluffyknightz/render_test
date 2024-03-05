package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Role;
import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.repositories.RoleRepository;
import com.spcu.dims.spcu_dims.repositories.UserRepository;
import com.spcu.dims.spcu_dims.services.UserServices;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImplement implements UserServices {


    String doesNotExistMessage = "Role with the name '%s' does not exist.";

    String userExistsMessage = "Username with the name '%s' already exists.";
    String userDoesNotExistMessage = "Username with the name '%s' does not exist.";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsernameCaseSensitive(String username) {
        return userRepository.findByUsernameCaseSensitive(username);
        }

    @Override
    public Object createUser(String username, String role) {
        Optional<Role> existRole = roleRepository.findByName(role);
        Optional<User> existUser = userRepository.findByUsername(username);

        if (existRole.isEmpty()) {
            return String.format(doesNotExistMessage, role);
        }

        if(existUser.isPresent()) {
            return String.format(userExistsMessage, username);
        }
        User user = User.builder()
                        .username(username)
                .role(existRole.get())
                .password(passwordEncoder.encode("1122121"))
                .build();
        return userRepository.save(user);
    }
}

