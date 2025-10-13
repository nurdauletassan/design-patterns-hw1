package com.solid.onlinelearning.models;

import java.util.UUID;

public class Student {
    private String id;
    private String name;
    private String email;

    public Student(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // --- Setters ---
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Student: %s (%s)", name, email);
    }
}
