package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Injects UserRepository for database operations related to users

    @Autowired
    private RoleRepository roleRepository; // Injects RoleRepository for database operations related to roles

    // Method to validate user login credentials
    public boolean validateLogin(String username, String password) {
        List<User> users = userRepository.findAll(); // Retrieves all users from the database
        HashMap<String, String> credentialsMap = new HashMap<>(); // Creates a HashMap to store usernames and passwords

        // Iterates through each user
        for (User user : users) {
            credentialsMap.put(user.getUsername(), user.getPassword()); // Maps each username to its password
        }

        return credentialsMap.containsKey(username) && credentialsMap.get(username).equals(password); // Checks if the username exists and if the password matches
    }

    // Method to register a new user
    public void registerUser(User user) {
        // Fetch the USER role from the database
        Role userRole = roleRepository.findByRoleName("USER");

        user.getRoles().add(userRole); // Assign the USER role to the new user
        userRepository.save(user); // Saves the new user to the database
    }

    // Method to check if a username already exists in the database
    public boolean usernameExists(String username) {
        return userRepository.findById(username).isPresent(); // Returns true if the username exists, false otherwise
    }
}