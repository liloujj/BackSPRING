package com.example.fullstack.controller;
import com.example.fullstack.model.Student;
import com.example.fullstack.model.University;
import com.example.fullstack.repository.StudentRepository;
import com.example.fullstack.repository.UniversityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentRepository studentRepository;

    private University persistedUniversity;

    @BeforeEach
    void setUp() {
        // clear DB and create a university for tests
        studentRepository.deleteAll();
        universityRepository.deleteAll();

        persistedUniversity = new University();
        persistedUniversity.setName("Test University");
        
        persistedUniversity = universityRepository.save(persistedUniversity);
    }

    @Test
    void shouldSaveStudent() throws Exception {
        // payload with nested university object containing existing id
        Map<String, Object> payload = Map.of(
            "name", "John Doe",
            "address", "456 Example Ave",
            "university", Map.of("id", persistedUniversity.getId())
        );

        mvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
            .andExpect(status().isOk()); // or isCreated() if your controller returns 201

        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(1);
        Student saved = students.get(0);
        assertThat(saved.getUniversity()).isNotNull();
        assertThat(saved.getUniversity().getId()).isEqualTo(persistedUniversity.getId());
    }

    @Test
    void shouldFindAllStudents() throws Exception {
        // create a student directly (setup) to verify GET /students
        Student student = new Student();
        student.setName("Alice");
        student.setAddress("789 Another Rd");
        student.setUniversity(persistedUniversity);
        studentRepository.save(student);

        mvc.perform(get("/students")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1)); // expecting array root

        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getUniversity().getId()).isEqualTo(persistedUniversity.getId());
    }

    // If your controller expects universityId instead of nested object, use:
    // Map.of("name","...", "address","...", "universityId", persistedUniversity.getId());
}