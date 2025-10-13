package com.solid.onlinelearning.repository;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.models.Course;

import java.util.List;


public class DatabaseCourseRepository implements CourseRepository {

    @Override
    public void addCourse(Course course) {
        System.out.println("[DatabaseRepo] Insert course into database: " + course.getTitle());
    }

    @Override
    public Course getCourseById(String id) {
        System.out.println("[DatabaseRepo] Fetch course by ID: " + id);
        // Возвратим пустую заглушку для демонстрации
        return new Course("DB Placeholder Course", "Loaded from DB", 0.0, "N/A");
    }

    @Override
    public List<Course> getAllCourses() {
        System.out.println("[DatabaseRepo] Fetch all courses from database");
        return List.of(); // Пустой список-заглушка
    }
}
