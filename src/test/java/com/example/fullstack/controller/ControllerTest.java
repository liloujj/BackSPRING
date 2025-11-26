package com.example.fullstack.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullstack.FullstackApplication;
import com.example.fullstack.model.Student;
import com.example.fullstack.model.University;
import com.example.fullstack.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest(classes = FullstackApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    // @Autowired
    // private StudentRepository studentRepository;

    // @Test
    // @Order(1)
    // void shouldSaveStudent() {

    //     University uni = new University();
    //     uni.setId(Long.valueOf(1)); // Set ID and required fields
        

    //     Student student = new Student();
    //     student.setName("Test Name");
    //     student.setAddress("Test Address");
    //     student.setUniversity(uni); // Make sure this is set and not null

        
    //     studentRepository.save(student);
    //     assertThat(studentRepository.count()).isEqualTo(1);
    // }

    // @Test
    // @Order(2)
    // void shouldFindAllStudents() {
    //     List<Student> students = studentRepository.findAll();
    //     assertThat(students).hasSize(1);
    //     assertThat(students.get(0).getName()).isEqualTo("Charlie");
    // }

    // Example: Fix in ControllerTest.shouldSaveStudent

    // Save student, then assert results
}
