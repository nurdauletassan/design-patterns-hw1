package com.solid.onlinelearning.repository;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.models.Course;

import java.util.ArrayList;
import java.util.List;


public class InMemoryCourseRepository implements CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("[InMemoryRepo] Added course: " + course.getTitle());
    }

    @Override
    public Course getCourseById(String id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }
}
