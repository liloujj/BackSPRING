package com.example.fullstack.controller;

import com.example.fullstack.model.University;
import com.example.fullstack.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
@CrossOrigin(origins = "http://localhost:3000") // autorise ton frontend React
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    // 🔹 Ajouter une université
    @PostMapping("/add")
    public String addUniversity(@RequestBody University university) {
        universityRepository.save(university);
        return "University added successfully!";
    }

    // 🔹 Afficher toutes les universités
    @GetMapping("/getAll")
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // 🔹 Supprimer une université
    @DeleteMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Long id) {
        universityRepository.deleteById(id);
        return "University deleted successfully!";
    }

    // 🔹 Modifier une université
    @PutMapping("/update/{id}")
    public String updateUniversity(@PathVariable Long id, @RequestBody University newUniversity) {
        University existing = universityRepository.findById(id).orElse(null);
        if (existing == null)
            return "University not found!";
        existing.setName(newUniversity.getName());
        universityRepository.save(existing);
        return "University updated successfully!";
    }
}
