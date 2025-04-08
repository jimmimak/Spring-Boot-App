package com.example.demo.service;

import com.example.demo.domain.Conference;
import com.example.demo.repository.ConferenceRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository; // Injects ConferenceRepository for database operations related to conferences

    // Defines a method to search conferences based on a query string
    public List<Conference> searchConferences(String query) {
        // If query is empty, return all conferences
        if (StringUtils.isBlank(query)) {
            return conferenceRepository.findAll();
        }
        // If query is not blank
        else {
            // Return conferences that match the query in name or description
            return conferenceRepository.findByNameOrDescriptionContainingIgnoreCase(query);
        }
    }
}