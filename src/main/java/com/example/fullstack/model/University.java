package com.example.fullstack.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class University{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "university_id")
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> students;

    public University(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

}
