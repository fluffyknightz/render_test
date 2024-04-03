package com.spcu.dims.spcu_dims.repositories;

import com.spcu.dims.spcu_dims.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  boolean existsByName(String roleName);

  Optional<Role> findByName(String role);

  @Query("SELECT e.name FROM Role e WHERE e.isDelete = false")
  List<String> findAllNotDeleted();
}
