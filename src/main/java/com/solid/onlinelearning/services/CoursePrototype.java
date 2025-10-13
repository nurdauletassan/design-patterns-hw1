package com.solid.onlinelearning.services;

import com.solid.onlinelearning.models.Course;

/**
 * CoursePrototype — вспомогательный класс для демонстрации использования паттерна Prototype.
 * Этот класс отвечает за клонирование объектов Course.
 */
public class CoursePrototype {

    // Метод для клонирования объекта Course
    public Course cloneCourse(Course originalCourse) {
        // Клонируем объект, возвращая новую копию
        return (Course) originalCourse.clone();
    }
}
