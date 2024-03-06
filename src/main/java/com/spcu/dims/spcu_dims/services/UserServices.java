package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.Optional;

public interface UserServices {

    Object createUser(String username, String role);

    DataTablesOutput<User> tableUser(DataTablesInput input);

    User deleteUser(int id);

    Object update(int id, String username, String role);
}
