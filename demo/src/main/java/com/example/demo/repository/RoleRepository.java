package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// Extends JpaRepository to provide standard CRUD operations for Role entities, with the primary key type Long
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Custom method to find a Role by its roleName
    // This method will query the database for a role that matches the provided roleName
    Role findByRoleName(String roleName);
}