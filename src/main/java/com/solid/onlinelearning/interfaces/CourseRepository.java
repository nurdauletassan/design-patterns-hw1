package com.solid.onlinelearning.interfaces;

import com.solid.onlinelearning.models.Course;
import java.util.List;

public interface CourseRepository {
    void addCourse(Course course);
    Course getCourseById(String id);
    List<Course> getAllCourses();
}
