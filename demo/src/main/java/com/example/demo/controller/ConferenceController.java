package com.example.demo.controller;

import com.example.demo.domain.Conference;
import com.example.demo.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // Marks this class as a Spring MVC controller
public class ConferenceController {

    @Autowired // Automatically injects an instance of ConferenceService
    private ConferenceService conferenceService;

    @GetMapping("/searchConference") // Maps HTTP GET requests to /searchConference to this method
    public String searchConference(@RequestParam(required = false) String query, Model model) {
        List<Conference> conferences = conferenceService.searchConferences(query); // Calls the service to search for conferences
        model.addAttribute("conferences", conferences); // Adds the list of conferences to the model, accessible in the view on the web browser
        return "visitor_dashboard"; // Specifies the Thymeleaf template to render (visitor_dashboard.html)
    }
}