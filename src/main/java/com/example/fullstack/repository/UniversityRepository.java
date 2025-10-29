package com.example.fullstack.repository;

import com.example.fullstack.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    University findByName(String name); // 👈 ajoute cette ligne
}
