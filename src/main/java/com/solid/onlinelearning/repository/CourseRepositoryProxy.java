package com.solid.onlinelearning.repository;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.services.LoggerSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseRepositoryProxy — прокси над репозиторием, добавляющий кэширование и логирование доступа.
 */
public class CourseRepositoryProxy implements CourseRepository {

    private final CourseRepository target;
    private final Map<String, Course> cache = new HashMap<>();
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public CourseRepositoryProxy(CourseRepository target) {
        this.target = target;
    }

    @Override
    public synchronized void addCourse(Course course) {
        cache.put(course.getId(), course);
        logger.log("[Proxy] Course cached locally: " + course.getTitle());
        target.addCourse(course);
    }

    @Override
    public synchronized Course getCourseById(String id) {
        if (cache.containsKey(id)) {
            logger.log("[Proxy] Returned course from cache: " + id);
            return cache.get(id);
        }

        Course course = target.getCourseById(id);
        if (course != null) {
            cache.put(id, course);
            logger.log("[Proxy] Fetched course from target repo and cached: " + id);
        }
        return course;
    }

    @Override
    public synchronized List<Course> getAllCourses() {
        List<Course> merged = new ArrayList<>(target.getAllCourses());
        for (Course cachedCourse : cache.values()) {
            if (merged.stream().noneMatch(c -> c.getId().equals(cachedCourse.getId()))) {
                merged.add(cachedCourse);
            }
        }
        logger.log("[Proxy] Returning combined course list, size=" + merged.size());
        return merged;
    }
}
