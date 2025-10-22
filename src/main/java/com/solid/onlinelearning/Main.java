package com.solid.onlinelearning;

import com.solid.onlinelearning.models.*;
import com.solid.onlinelearning.services.*;
import com.solid.onlinelearning.utils.Logger;

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

        // Логирование для исходного и клонированного курса
        logger.log("Original Course Price: " + javaCourse.getPrice());  // 100.0
        logger.log("Cloned Course Price: " + clonedCourse.getPrice());  // 120.0

         // Использование Bridge Pattern для обработки платёжей
        // Используем Stripe для обработки платежей через Bridge
        PaymentProcessor stripeProcessor = new StripePaymentProcessor();
        PaymentBridge stripeBridge = new PaymentBridge(stripeProcessor);
        stripeBridge.executePayment(200.0);

        // Используем PayPal через Adapter (адаптируем интерфейс)
        PayPalPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        PaymentProcessorAdapter paypalAdapter = new PaymentProcessorAdapter(paypalProcessor);
        paypalAdapter.processPayment(150.0);

        // Логирование для выполненных платежей
        logger.log("Payments processed successfully.");
    }
}
