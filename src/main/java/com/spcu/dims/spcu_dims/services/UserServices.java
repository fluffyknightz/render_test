package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface UserServices {

    Object createUser(String username, String role, String password);

    DataTablesOutput<User> tableUser(DataTablesInput input);

    User deleteUser(int id);

    Object update(int id, String username, String role, String password);

    User passwordChange(String password, String username);
}
