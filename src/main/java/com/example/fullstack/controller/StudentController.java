package com.example.fullstack.controller;

import com.example.fullstack.model.Student;
import com.example.fullstack.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // autorise React à se connecter
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ➕ Ajouter un étudiant
    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "New student is added";
    }

    // 📋 Récupérer tous les étudiants
    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 📚 Récupérer tous les étudiants + nom de leur université
    @GetMapping("/getAllUniv")
    public List<Object> getAllStudentsUniversity() {
        return studentService.getAllStudentsUniversity();
    }

    // 🔍 Trouver les étudiants d’une université donnée
    @GetMapping("/findStudUniv")
    public List<Object> findStudentsByUniversity(@RequestParam String univName) {
        return studentService.findStudentsByUniversity(univName);
    }
}
