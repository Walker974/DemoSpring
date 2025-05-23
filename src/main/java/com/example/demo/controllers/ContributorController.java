package com.example.demo.controllers;

import com.example.demo.dto.ContributorDto;
import com.example.demo.entities.Contributors;
import com.example.demo.mapper.ContributorConverter;
import com.example.demo.services.ContributorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping ("/api/v1")
public class ContributorController {
    // This class will handle HTTP requests related to user management
    // For example, endpoints to create, update, delete, and retrieve users
    // It will use the UserService to perform the actual operations
    @Autowired
    private ContributorService contributorService;

    // Example endpoint to create a new user
    @RequestMapping(value = "/contributor", method = RequestMethod.POST)
    @Operation(summary = "Create a new contributor", description = "Create a new contributor with the provided details")
    public ResponseEntity<ContributorDto> createContributor(@RequestBody ContributorDto contributors) {
        contributorService.createContributor(ContributorConverter.toEntity(contributors));
        return ResponseEntity.status(HttpStatus.CREATED).body(contributors);
    }

    @RequestMapping(value = "/contributor/{cId}", method = RequestMethod.GET)
    @Operation(summary = "Get contributor by ID", description = "Retrieve a contributor by their ID")
    public ResponseEntity<ContributorDto> getContributorById(@PathVariable Long cId) {
        ContributorDto contributor = ContributorConverter.toDto(contributorService.getContributorById(cId));
        return ResponseEntity.status(HttpStatus.OK).body(contributor);
    }

    @RequestMapping(value = "/contributors", method = RequestMethod.GET)
    @Operation(summary = "Get all contributors", description = "Retrieve a list of all contributors")
    public ResponseEntity<List<ContributorDto>> getAll() {
        List<ContributorDto> contributors = ContributorConverter.toDtoList(contributorService.getAll());
        if (contributors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(contributors);
    }

    @RequestMapping(value = "/contributor/{cId}", method = RequestMethod.PUT)
    @Operation(summary = "Update contributor", description = "Update the details of an existing contributor")
    public ResponseEntity<ContributorDto> updateContributor(@PathVariable Long cId, @RequestBody ContributorDto data) {
        ContributorDto contributor = ContributorConverter.toDto(contributorService.updateContributor(cId, ContributorConverter.toEntity(data)));
        if (contributor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(contributor);
    }

    @RequestMapping(value = "/contributor/{cId}", method = RequestMethod.DELETE)
    public void deleteContributor(@PathVariable Long cId) {

        contributorService.deleteContributor(cId);
    }
}
