package com.example.fullstack.repository;

import com.example.fullstack.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Tous les étudiants avec le nom de leur université
    @Query("SELECT s.name, u.name FROM Student s, University u WHERE s.university.id = u.id")
    List<Object> getAllStudentsUniversity();

    // Étudiants d’une université spécifique
    @Query("SELECT s.name, u.name FROM Student s, University u WHERE s.university.id = u.id AND u.name = :param")
    List<Object> findStudentsByUniversity(@Param("param") String univName);
}
