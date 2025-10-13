package com.solid.onlinelearning.models;

import com.solid.onlinelearning.interfaces.Prototype;
import java.util.UUID;

/**
 * Course — модель курса, реализует паттерн Prototype для клонирования.
 */
public class Course implements Prototype {
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

    // Реализация метода clone() для клонирования курса
    @Override
    public Prototype clone() {
        return new Course(this.title, this.description, this.price, this.instructorId);
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getInstructorId() { return instructorId; }

    // Метод для установки новых значений после клонирования (например, цена)
    public void setPrice(double price) {
        this.price = price;
    }
}
