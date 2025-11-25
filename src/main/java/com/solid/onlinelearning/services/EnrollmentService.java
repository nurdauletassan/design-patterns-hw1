package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.*;
import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.models.Student;

public class EnrollmentService {

    private final CourseRepository courseRepository;
    private final PaymentProcessor paymentProcessor;
    private final NotificationService notificationService;
    private final DiscountPolicy discountPolicy;

    public EnrollmentService(CourseRepository courseRepository,
                             PaymentProcessor paymentProcessor,
                             NotificationService notificationService,
                             DiscountPolicy discountPolicy) {
        this.courseRepository = courseRepository;
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
        this.discountPolicy = discountPolicy;
    }

    public void enrollStudent(Student student, Course course) {
        double discountedPrice = discountPolicy.applyDiscount(course.getPrice());
        enrollStudentWithPrice(student, course, discountedPrice);
    }

    /**
     * Variant used when the price is pre-approved via the chain of responsibility.
     */
    public void enrollStudentWithPrice(Student student, Course course, double priceToCharge) {
        boolean paid = paymentProcessor.processPayment(priceToCharge);

        if (paid) {
            notificationService.sendNotification(
                    student.getEmail(),
                    "You have successfully enrolled in course: " + course.getTitle()
            );
            System.out.println("[ENROLLMENT] Student " + student.getName() +
                    " enrolled in course " + course.getTitle());
        } else {
            notificationService.sendNotification(
                    student.getEmail(),
                    "Payment failed for course: " + course.getTitle()
            );
        }
    }
}
