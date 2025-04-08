package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Declares the UserRepository interface extending JpaRepository,
// indicating it's a repository layer for the User entity, with the primary key of type String
// Inherits all the default CRUD operations from JpaRepository for User entities
// such as save, findById, findAll, deleteById, etc.
@Repository // Marks this interface as a Spring Data Repository
public interface UserRepository extends JpaRepository<User, String> {}