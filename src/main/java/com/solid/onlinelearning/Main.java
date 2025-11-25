package com.solid.onlinelearning;

import com.solid.onlinelearning.interfaces.PaymentProcessor;
import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.models.content.Lesson;
import com.solid.onlinelearning.models.content.Module;
import com.solid.onlinelearning.models.content.Quiz;
import com.solid.onlinelearning.services.CourseBuilder;
import com.solid.onlinelearning.services.CoursePrototype;
import com.solid.onlinelearning.services.FraudCheckPaymentDecorator;
import com.solid.onlinelearning.services.LoggingPaymentDecorator;
import com.solid.onlinelearning.services.LoggerSingleton;
import com.solid.onlinelearning.services.PaymentServiceImpl;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ — Создание исходного курса с использованием Builder
        CourseBuilder builder = new CourseBuilder();
        Course javaCourse = builder.setTitle("Java Fundamentals")
                .setDescription("Learn Java from scratch")
                .setPrice(100.0)
                .setInstructorId("instructor1")
                .build();

        // Логирование с использованием Singleton
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("Course created: " + javaCourse.getTitle());

        // 2️⃣ — Клонирование курса с использованием Prototype
        CoursePrototype coursePrototype = new CoursePrototype();
        Course clonedCourse = coursePrototype.cloneCourse(javaCourse);
        clonedCourse.setPrice(120.0);  // Изменение цены для клона

        logger.log("Original Course Price: " + javaCourse.getPrice());  // 100.0
        logger.log("Cloned Course Price: " + clonedCourse.getPrice());  // 120.0

        // 3️⃣ — Decorator: динамическое расширение логики оплаты
        PaymentProcessor basePaymentProcessor = new PaymentServiceImpl();
        PaymentProcessor securedProcessor = new LoggingPaymentDecorator(
                new FraudCheckPaymentDecorator(basePaymentProcessor, 500.0));

        securedProcessor.processPayment(350.0); // пройдет фрод-проверку
        securedProcessor.processPayment(1200.0); // заблокируется декоратором FraudCheck

        // 4️⃣ — Composite: дерево учебных модулей
        Module rootPath = new Module("Java Fundamentals Path");

        Module basics = new Module("Java Basics");
        basics.add(new Lesson("Syntax and Types", 45));
        basics.add(new Lesson("Control Flow", 35));
        basics.add(new Quiz("Basics Quiz", 15));

        Module oop = new Module("Object-Oriented Programming");
        oop.add(new Lesson("Classes and Objects", 40));
        oop.add(new Lesson("Inheritance and Interfaces", 50));
        oop.add(new Quiz("OOP Quiz", 20));

        rootPath.add(basics);
        rootPath.add(oop);
        rootPath.add(new Lesson("Collections Overview", 30));

        System.out.println("--- Course Content Structure (Composite) ---");
        rootPath.print("");
        logger.log("Total duration for path: " + rootPath.getDurationMinutes() + " minutes");
    }
}
