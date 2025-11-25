package com.solid.onlinelearning;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.interfaces.PaymentProcessor;
import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.models.Student;
import com.solid.onlinelearning.models.content.LearningComponent;
import com.solid.onlinelearning.models.content.LearningComponentFactory;
import com.solid.onlinelearning.repository.CourseRepositoryProxy;
import com.solid.onlinelearning.repository.DatabaseCourseRepository;
import com.solid.onlinelearning.services.LearningPlatformFacade;
import com.solid.onlinelearning.services.LoggerSingleton;
import com.solid.onlinelearning.services.PayPalPaymentProcessor;
import com.solid.onlinelearning.services.PaymentBridge;
import com.solid.onlinelearning.services.PaymentProcessorAdapter;
import com.solid.onlinelearning.services.StripePaymentProcessor;
import com.solid.onlinelearning.services.command.CommandInvoker;
import com.solid.onlinelearning.services.command.EnrollStudentCommand;
import com.solid.onlinelearning.services.command.UpdateCoursePriceCommand;

public class Main {
    public static void main(String[] args) {

        LoggerSingleton logger = LoggerSingleton.getInstance();

        // Facade wires together builders, prototype, discounts, payments, and notifications.
        CourseRepository proxiedRepo = new CourseRepositoryProxy(new DatabaseCourseRepository());
        LearningPlatformFacade facade = new LearningPlatformFacade(proxiedRepo);

        Course javaCourse = facade.publishCourse(
                "Java Fundamentals",
                "Learn Java from scratch",
                100.0,
                "instructor1"
        );
        Course clonedCourse = facade.duplicateCourse(javaCourse, 120.0);
        logger.log("Facade created courses: original=" + javaCourse.getPrice() + ", clone=" + clonedCourse.getPrice());

        // Proxy pattern: repeated access goes through cache inside CourseRepositoryProxy.
        proxiedRepo.getCourseById(javaCourse.getId());
        proxiedRepo.getCourseById(javaCourse.getId()); // served from cache

        // Enroll with configurable discount and payment processor via the facade.
        Student student = new Student("Alice", "alice@example.com");
        facade.enrollStudent(student, javaCourse.getId(), "Student", "Stripe");
        // Chain of Responsibility rejecting suspicious enrollment
        facade.enrollStudent(new Student("Mallory", "fraudster@example.com"), javaCourse.getId(), "Student", "Stripe");

        // Flyweight pattern: lessons with identical intrinsic data reuse the same object.
        LearningComponentFactory componentFactory = new LearningComponentFactory();
        LearningComponent introLessonA = componentFactory.getLesson("Intro to SOLID", "video", 12);
        LearningComponent introLessonB = componentFactory.getLesson("Intro to SOLID", "video", 12);
        LearningComponent summary = componentFactory.getLesson("Patterns Recap", "article", 5);
        introLessonA.render(student.getId(), 10);
        introLessonB.render("student-2", 85); // reused flyweight
        summary.render(student.getId(), 45);
        logger.log("Flyweight cache size: " + componentFactory.cachedFlyweightsCount());

        // Command pattern: queue actions and support undo/redo.
        CommandInvoker invoker = new CommandInvoker();
        invoker.execute(new UpdateCoursePriceCommand(javaCourse, 95.0));
        invoker.undoLast();
        invoker.redoLast();
        invoker.execute(new EnrollStudentCommand(facade, student, clonedCourse.getId(), "BlackFriday", "PayPal"));

        // Bridge + Adapter demonstrations remain available.
        PaymentProcessor stripeProcessor = new StripePaymentProcessor();
        PaymentBridge stripeBridge = new PaymentBridge(stripeProcessor);
        stripeBridge.executePayment(200.0);

        PayPalPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        PaymentProcessorAdapter paypalAdapter = new PaymentProcessorAdapter(paypalProcessor);
        paypalAdapter.processPayment(150.0);

        logger.log("Platform flow completed.");
    }
}
