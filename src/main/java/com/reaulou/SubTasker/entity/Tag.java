package com.reaulou.SubTasker.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Many-to-many relationship with Task
    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks = new HashSet<>();

    // Default constructor
    public Tag() {
    }

    // Parameterized constructor
    public Tag(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString() method
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
