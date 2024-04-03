package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Role;
import com.spcu.dims.spcu_dims.entities.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends DataTablesRepository<User, Integer> {

    @Query(value = "SELECT * FROM users u WHERE BINARY u.username = :username", nativeQuery = true)
    Optional<User> findByUsernameCaseSensitive(@Param("username") String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndIdNot(String username, int id);

    List<User> findByRole(Role role);
}
