package com.example.demo.services;

import com.example.demo.dao.ContributorRepository;
import com.example.demo.entities.Contributors;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributorService {
    private final ContributorRepository contributorRepository;
    // This class will contain the business logic for user management
    // For example, methods to create, update, delete, and retrieve users
    // It will use the UserRepository to interact with the database

    // Example method to create a new user
    public ContributorService(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    };
    public void createContributor(Contributors contributors) {
        // Logic to create a new user
        // This would typically involve validating the input and saving the user to the database
        //User createdUser = User.builder()
        //        .name(user.getName())
        //        .email(user.getEmail())
         //       .password(user.getPassword())
        //        .build();
        contributorRepository.save(contributors);
    }

    public List<Contributors> getAll() {
        return contributorRepository.findAll();
    }

    // Example method to retrieve a user by email
    public Contributors getContributorById(Long id) {
        return contributorRepository.findById(id).orElse(null);
    }

    public void deleteContributor(Long id) {
        contributorRepository.deleteById(id);
    }


}
