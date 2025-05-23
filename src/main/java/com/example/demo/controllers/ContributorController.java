package com.example.demo.controllers;

import com.example.demo.entities.Contributors;
import com.example.demo.services.ContributorService;
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
    public ResponseEntity<Contributors> createContributor(@RequestBody Contributors contributors) {
        contributorService.createContributor(contributors);
        return ResponseEntity.status(HttpStatus.CREATED).body(contributors);
    }

    @RequestMapping(value = "/contributor/{cId}", method = RequestMethod.GET)
    public ResponseEntity<Contributors> getContributorById(@PathVariable Long cId) {
        Contributors contributor = contributorService.getContributorById(cId);
        return ResponseEntity.status(HttpStatus.OK).body(contributor);
    }

    @RequestMapping(value = "/contributors", method = RequestMethod.GET)
    public ResponseEntity<List<Contributors>> getAll() {
        List<Contributors> contributors = contributorService.getAll();
        if (contributors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(contributors);
    }

    @RequestMapping(value = "/contributor/{cId}", method = RequestMethod.PUT)
    public ResponseEntity<Contributors> updateContributor(@PathVariable Long cId, @RequestBody Contributors contributors) {
        Contributors contributor = contributorService.getContributorById(cId);
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
