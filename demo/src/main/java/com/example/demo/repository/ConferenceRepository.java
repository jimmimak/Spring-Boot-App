package com.example.demo.repository;

import com.example.demo.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository // Marks this interface as a Spring Data Repository
public interface ConferenceRepository extends JpaRepository<Conference, String> { // Extends JpaRepository to provide CRUD operations for Conference entities, with the primary key type String

    // Custom query to search by name or description
    @Query("SELECT c FROM Conference c WHERE lower(c.name) LIKE lower(concat('%', :searchTerm, '%')) " +
            "OR lower(c.description) LIKE lower(concat('%', :searchTerm, '%'))")
    List<Conference> findByNameOrDescriptionContainingIgnoreCase(String searchTerm); // This method takes a search term and returns a list of conferences where either the name or description contains the search term, case-insensitive
}