package com.example.demo.services;

import com.example.demo.dao.ContributorRepository;
import com.example.demo.dto.ContributorDto;
import com.example.demo.entities.Contributors;
import com.example.demo.validator.EmailValidator;
import jakarta.transaction.Transactional;
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
    }

    private void validateContributor(Contributors contributors) {
        if (contributors.getId() != null) {
            throw new IllegalArgumentException("ID should be null for new contributors");
        }
        if (contributors.getName() == null || contributors.getEmail() == null || contributors.getPassword() == null) {
            throw new IllegalArgumentException("Name, email, and password cannot be null");
        }
        if (!EmailValidator.isValidEmail(contributors.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void createContributor(Contributors contributors) {
        validateContributor(contributors);
        contributorRepository.save(contributors);
    }

    public List<Contributors> getAll() {
        return contributorRepository.findAll();
    }

    // Example method to retrieve a user by email
    public Contributors getContributorById(Long id) {
        return contributorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteContributor(Long id) {
        // Logic to delete a user by ID
        contributorRepository.deleteById(id);
    }

    @Transactional
    public Contributors updateContributor(Long id, Contributors data) {
        Contributors existingContributor = contributorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contributor not found with ID: " + id));

        if (data.getName() != null) {
            existingContributor.setName(data.getName());
        }
        if (data.getEmail() != null) {
            if (!EmailValidator.isValidEmail(data.getEmail())) {
                throw new IllegalArgumentException("Invalid email format");
            }
            existingContributor.setEmail(data.getEmail());
        }

        // Save the updated contributor
        return contributorRepository.save(existingContributor);
    }


}
