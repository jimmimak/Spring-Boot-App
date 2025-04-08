package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity // Marks this class as a JPA entity
@Table(name = "conference") // Specifies the table in the database to which this entity is mapped
public class Conference {

    @Id // Marks this field as the primary key
    @Column(name = "Name") // Maps this field to the column named 'Name' in the 'conference' table inside the database
    @Getter // Lombok annotation to automatically generate a getter method for this field
    private String name;

    @Column(name = "Description") // Maps this field to the column named 'Description' in the 'conference' table inside the database
    @Getter // Lombok annotation to automatically generate a getter method for this field
    private String description;

    @Column(name = "Creationdate") // Maps this field to the column named 'Creationdate' in the 'conference' table inside the database
    private String creationDate;

    @Column(name = "State") // Maps this field to the column named 'State' in the 'conference' table inside the database
    private String state;

    public Conference() {} // Default constructor required by JPA

    public Conference(String name, String description, String creationDate, String state) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.state = state;
    }
}