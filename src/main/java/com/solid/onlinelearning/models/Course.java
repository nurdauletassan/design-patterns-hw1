package com.solid.onlinelearning.models;

import java.util.UUID;

public class Course {
    private String id;
    private String title;
    private String description;
    private double price;
    private String instructorId;

    public Course(String title, String description, double price, String instructorId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.price = price;
        this.instructorId = instructorId;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getInstructorId() {
        return instructorId;
    }

    // --- Setters ---
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return String.format("Course: %s (%.2f USD)", title, price);
    }
}
