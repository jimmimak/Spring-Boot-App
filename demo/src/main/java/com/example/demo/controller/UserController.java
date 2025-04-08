package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller // Marks this class as a Spring MVC controller
public class UserController {

    @Autowired // Injects UserService automatically
    private UserService userService;

    @GetMapping("/") // Handles requests to the root URL
    public String home() {
        return "index"; // Returns the index.html template
    }

    @GetMapping("/login") // Handles GET requests to /login URL
    public String login() {
        return "login";
    } // Returns the login.html template

    @PostMapping("/login") // Handles POST requests to /login URL
    public ModelAndView loginUser(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  ModelAndView modelAndView) {
        // If the user successfully logs in
        if (userService.validateLogin(username, password)) {
            modelAndView.setViewName("redirect:/authenticateduser_dashboard"); // Redirects to dashboard on successful login
        }
        // If the log in function fails
        else {
            modelAndView.addObject("loginError", "Invalid username or password."); // Adds an error message to the model if login fails in order for it to appear on the web browser
            modelAndView.setViewName("login"); // Redirects back to the login page
        }
        return modelAndView; // Returns the ModelAndView object with the view name and attributes
    }

    @GetMapping("/register") // Handles GET requests to /register URL
    public String register() {
        return "register";
    } // Returns the register.html template

    @PostMapping("/register") // Handles POST requests to /register URL
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("fullName") String fullName,
                               Model model) {
        // Validate Username and Password
        String usernameValidation = validateUsername(username); // Validates the username
        String passwordValidation = validatePassword(password); // Validates the password

        if (!usernameValidation.equals("valid") || !passwordValidation.equals("valid")) {
            model.addAttribute("usernameError", usernameValidation); // Adds username validation error message to model
            model.addAttribute("passwordError", passwordValidation); // Adds password validation error message to model
            return "register"; // Return to the registration page with error messages
        }

        // Check if username already exists
        if (userService.usernameExists(username)) {
            model.addAttribute("usernameError", "Username already exists. Please choose a different username.");
            return "register"; // Returns to the registration page if username exists
        }

        User newUser = new User(username, password, fullName); // Creates a new User object
        userService.registerUser(newUser); // Registers the new user
        return "redirect:/login"; // Redirects to the login page after successful registration
    }

    // Method for checking if the username which the user provided abides by the username format rules we want
    private String validateUsername(String username) {
        if (!username.matches("^[A-Za-z][A-Za-z0-9_]{4,}$")) {
            return "Username must begin with a letter and should comprise of at least 5 alphanumeric characters or '_'.";
        }
        return "valid"; // Returns 'valid' if the username is valid
    }

    // Method for checking if the password which the user provided abides by the password format rules we want
    private String validatePassword(String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!~$%^&*])[A-Za-z\\d!~$%^&*]{8,}$")) {
            return "Password must be at least 8 characters long and include uppercase and lowercase letters, digits, and special characters (e.g., !, ~, $).";
        }
        return "valid"; // Returns 'valid' if the password is valid
    }

    @GetMapping("/guest") // Handles GET requests to /guest URL
    public String continueAsGuest(Model model) {
        model.addAttribute("isGuest", true); // Adds an attribute to mark the user as a guest
        return "visitor_dashboard"; // Returns the visitor_dashboard.html template
    }

    @GetMapping("/authenticateduser_dashboard") // Handles GET requests to /authenticateduser_dashboard URL
    public String authenticatedUserDashboard() {
        return "authenticateduser_dashboard"; // Returns the authenticateduser_dashboard.html template
    }
}