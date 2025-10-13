package com.solid.onlinelearning.services;

import com.solid.onlinelearning.models.Course;

public class CourseBuilder {
    private String title;
    private String description;
    private double price;
    private String instructorId;

    // Шаг 1: Установим название курса
    public CourseBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    // Шаг 2: Установим описание курса
    public CourseBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    // Шаг 3: Установим цену курса
    public CourseBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    // Шаг 4: Установим ID преподавателя
    public CourseBuilder setInstructorId(String instructorId) {
        this.instructorId = instructorId;
        return this;
    }

    // Финальный метод для создания объекта Course
    public Course build() {
        return new Course(title, description, price, instructorId);
    }
}
