package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a JPA entity
@Table(name = "role") // Specifies the table in the database to which this entity is mapped
public class Role {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way the ID is generated (auto-increment in this case)
    @Column(name = "Roleid") // Maps this field to the column named 'Roleid' in the 'role' table inside the database
    private Long roleId;

    @Column(name = "Rolename") // Maps this field to the column named 'Rolename' in the 'role' table inside the database
    private String roleName;

    public Role() {} // Default constructor required by JPA

    // Constructor to create a Role instance with a specific roleName
    public Role(String roleName) {
        this.roleName = roleName;
    }
}