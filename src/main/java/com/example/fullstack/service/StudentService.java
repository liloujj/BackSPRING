package com.example.fullstack.service;

import com.example.fullstack.model.Student;     
import com.example.fullstack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ➕ Ajouter un étudiant
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // 📋 Récupérer tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 🖊️ Mettre à jour un étudiant
    public Student updateStudent(String id, Student student) {
        Long studentId = Long.parseLong(id);
        student.setId(studentId);
        return studentRepository.save(student);
    }

    // 🗑️ Supprimer un étudiant
    public void deleteStudent(String id) {
        Long studentId = Long.parseLong(id);
        studentRepository.deleteById(studentId);
    }

    // 📚 Récupérer tous les étudiants avec leur université
    public List<Object> getAllStudentsUniversity() {
        return studentRepository.getAllStudentsUniversity();
    }

    // 🔍 Rechercher les étudiants d’une université donnée
    public List<Object> findStudentsByUniversity(String univName) {
        return studentRepository.findStudentsByUniversity(univName);
    }
}
