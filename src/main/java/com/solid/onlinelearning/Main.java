package com.solid.onlinelearning;

import com.solid.onlinelearning.interfaces.*;
import com.solid.onlinelearning.models.*;
import com.solid.onlinelearning.repository.*;
import com.solid.onlinelearning.services.*;
import com.solid.onlinelearning.utils.Logger;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ — Создание зависимостей (интерфейсы + реализации)

        // Используем фабрику для создания платёжного процессора
        PaymentProcessor paymentProcessor = PaymentProcessorFactory.createPaymentProcessor("PayPal");

        // Используем фабрику для создания стратегии скидки
        DiscountPolicy discountPolicy = new DiscountPolicyFactoryImpl().createDiscountPolicy("Student");

        // Создание сервисов
        CourseRepository courseRepo = new InMemoryCourseRepository();
        NotificationService notificationService = new NotificationServiceImpl();

        CourseService courseService = new CourseService(courseRepo);
        EnrollmentService enrollmentService = new EnrollmentService(
                courseRepo,
                paymentProcessor,
                notificationService,
                discountPolicy
        );

        // 2️⃣ — Создание сущностей
        Instructor instructor = new Instructor("Dr. Smith", "smith@edu.com");
        Course javaCourse = new Course("Java Fundamentals", "Learn Java from zero", 100.0, instructor.getId());
        Student student = new Student("Alice Johnson", "alice@mail.com");

        // 3️⃣ — Добавление курса
        courseService.addCourse(javaCourse);
        Logger.info("Available courses: " + courseService.getAllCourses().size());

        // 4️⃣ — Зачисление студента
        enrollmentService.enrollStudent(student, javaCourse);
    }
}
