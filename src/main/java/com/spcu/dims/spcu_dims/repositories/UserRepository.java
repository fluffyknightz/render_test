package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM Users u WHERE BINARY u.username = :username", nativeQuery = true)
    Optional<User> findByUsernameCaseSensitive(@Param("username") String username);

}
