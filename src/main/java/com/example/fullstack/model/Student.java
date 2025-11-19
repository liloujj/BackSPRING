package com.example.fullstack.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    public Student() {
    }

    public Student(String name, String address, University university) {
        this.name = name;
        this.address = address;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public University getUniversity() {
        return university;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    
}