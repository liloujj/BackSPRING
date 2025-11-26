package com.example.fullstack.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullstack.FullstackApplication;
import com.example.fullstack.model.Student;
import com.example.fullstack.model.University;
import com.example.fullstack.repository.StudentRepository;
import com.example.fullstack.repository.UniversityRepository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest(classes = FullstackApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Test
    @Order(1)
    void shouldSaveStudent() {
        // 1. Persist a University first
        University uni = new University();
        uni.setName("Test University");

        uni = universityRepository.save(uni); // save to DB so it has an ID

        // 2. Create Student and set persisted University
        Student student = new Student();
        student.setName("Charlie");
        student.setAddress("456 Elm St");
        student.setUniversity(uni); // must reference persisted entity

        // 3. Save student
        studentRepository.save(student);

        // 4. Assert student saved
        assertThat(studentRepository.count()).isEqualTo(1);
        assertThat(studentRepository.findAll().get(0).getUniversity().getId()).isEqualTo(uni.getId());
    }

    @Test
    @Order(2)
    void shouldFindAllStudents() {
        List<Student> students = studentRepository.findAll();

        // Check student list
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("Charlie");
        assertThat(students.get(0).getUniversity().getName()).isEqualTo("Test University");
    }

    // Example: Fix in ControllerTest.shouldSaveStudent

    // Save student, then assert results
}
