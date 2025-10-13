package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.models.Course;
import java.util.List;


public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public Course findCourse(String id) {
        return courseRepository.getCourseById(id);
    }
}
