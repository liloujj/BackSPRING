package com.example.fullstack.service;

import com.example.fullstack.model.University;
import com.example.fullstack.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityByName(String name) {
        return universityRepository.findByName(name);
    }
}
