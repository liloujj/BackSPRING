package com.example.fullstack.controller;

import com.example.fullstack.model.Student;

import com.example.fullstack.repository.StudentRepository;
import com.example.fullstack.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student")
public class StudentController {
   
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    // ➕ Ajouter un étudiant
    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "New student is added";
    }

    // 📋 Récupérer tous les étudiants
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null)
            return "Student not found!";
        existing.setName(newStudent.getName());
        studentRepository.save(existing);
        return "Student updated successfully!";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null)
            return "Student not found!";
        studentRepository.deleteById(id);
        return "Student deleted successfully!";
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
