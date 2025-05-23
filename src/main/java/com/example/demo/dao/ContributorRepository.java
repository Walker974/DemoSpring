package com.example.demo.dao;

import com.example.demo.entities.Contributors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContributorRepository extends JpaRepository<Contributors, Long> {
    Contributors findByEmail(String email);
    Contributors findByName(String name);
    Contributors findById(long id);
}
