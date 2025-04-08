package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a JPA entity
@Table(name = "appuser") // Specifies the table in the database to which this entity is mapped
public class User {

    @Id // Marks this field as the primary key
    @Column(name = "Username") // Maps this field to the column named 'Username' in the 'appuser' table inside the database
    @Getter // Lombok annotation to automatically generate the getter method for this field
    private String username;

    @Column(name = "Password") // Maps this field to the column named 'Password' in the 'appuser' table inside the database
    @Getter // Lombok annotation to automatically generate the getter method for this field
    private String password;

    @Column(name = "Fullname") // Maps this field to the column named 'Fullname' in the 'appuser' table inside the database
    private String fullName;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // Configures a many-to-many relationship with the Role entity
    @JoinTable(
            name = "userrole", // Specifies the join table name
            joinColumns = @JoinColumn(name = "Username"), // Specifies the join column in the current entity (User)
            inverseJoinColumns = @JoinColumn(name = "Roleid") // Specifies the join column in the target entity (Role)
    )
    @Getter // Lombok annotation to automatically generate the getter method for this field
    private Set<Role> roles  = new HashSet<>(); // Stores the roles associated with the user

    public User() {} // Default constructor required by JPA

    // Constructor to create a User instance with specific details
    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }
}