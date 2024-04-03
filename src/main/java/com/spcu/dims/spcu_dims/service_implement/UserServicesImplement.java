package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Role;
import com.spcu.dims.spcu_dims.entities.User;
import com.spcu.dims.spcu_dims.repositories.RoleRepository;
import com.spcu.dims.spcu_dims.repositories.UserRepository;
import com.spcu.dims.spcu_dims.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Object createUser(String username, String role, String password) {
        Optional<Role> existRole = roleRepository.findByName(role);
        Optional<User> existUser = userRepository.findByUsername(username);

        if (existRole.isEmpty()) {
            return String.format(doesNotExistMessage, role);
        } else if (existUser.isPresent()) {
            return String.format(userExistsMessage, username);
        }
        User user = User.builder().username(username).role(existRole.get()).password(passwordEncoder.encode(password)).build();
        return userRepository.save(user);
    }

    @Override
    public DataTablesOutput<User> tableUser(DataTablesInput input) {
        return userRepository.findAll(input);
    }

    @Override
    public User deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setDelete(true);
            return userRepository.save(user.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String username, String role, String password) {

        Optional<User> userExist = userRepository.findByUsernameAndIdNot(username, id);
        if (userExist.isPresent()) {
            return String.format(userExistsMessage, username);
        }
        Optional<Role> roleExist = roleRepository.findByName(role);
        if (roleExist.isEmpty()) {
            return String.format(doesNotExistMessage, role);
        }
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return String.format(userDoesNotExistMessage, username);
        }
        if (password != null) {
            user.get().setPassword(passwordEncoder.encode(password));
        }
        user.get().setUsername(username);
        user.get().setRole(roleExist.get());

        return userRepository.save(user.get());
    }

    @Override
    public User passwordChange(String newPassword, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user.get());
            return user.get();

        }

        return null;
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }


}

