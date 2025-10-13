package com.solid.onlinelearning;

import com.solid.onlinelearning.interfaces.*;
import com.solid.onlinelearning.models.*;
import com.solid.onlinelearning.repository.*;
import com.solid.onlinelearning.services.*;
import com.solid.onlinelearning.utils.Logger;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ — Создание зависимостей (интерфейсы + реализации)
        CourseRepository courseRepo = new InMemoryCourseRepository();
        PaymentProcessor paymentProcessor = new PaymentServiceImpl();
        NotificationService notificationService = new NotificationServiceImpl();
        DiscountPolicy discountPolicy = new StudentDiscountPolicy();

        // 2️⃣ — Создание сервисов
        CourseService courseService = new CourseService(courseRepo);
        EnrollmentService enrollmentService = new EnrollmentService(
                courseRepo,
                paymentProcessor,
                notificationService,
                discountPolicy
        );

        // 3️⃣ — Создание сущностей
        Instructor instructor = new Instructor("Dr. Smith", "smith@edu.com");
        Course javaCourse = new Course("Java Fundamentals", "Learn Java from zero", 100.0, instructor.getId());
        Student student = new Student("Alice Johnson", "alice@mail.com");

        // 4️⃣ — Добавление курса
        courseService.addCourse(javaCourse);
        Logger.info("Available courses: " + courseService.getAllCourses().size());

        // 5️⃣ — Зачисление студента
        enrollmentService.enrollStudent(student, javaCourse);
    }
}
