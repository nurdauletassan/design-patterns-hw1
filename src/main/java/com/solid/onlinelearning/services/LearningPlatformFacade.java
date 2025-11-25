package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.CourseRepository;
import com.solid.onlinelearning.interfaces.DiscountPolicy;
import com.solid.onlinelearning.interfaces.NotificationService;
import com.solid.onlinelearning.interfaces.PaymentProcessor;
import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.models.Student;
import com.solid.onlinelearning.repository.CourseRepositoryProxy;
import com.solid.onlinelearning.repository.InMemoryCourseRepository;
import com.solid.onlinelearning.services.chain.EnrollmentApprovalChainBuilder;
import com.solid.onlinelearning.services.chain.EnrollmentHandler;
import com.solid.onlinelearning.services.chain.EnrollmentRequest;

/**
 * Facade that hides the orchestration of course creation, cloning, and enrollment.
 */
public class LearningPlatformFacade {

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final DiscountPolicyFactoryImpl discountPolicyFactory;
    private final PaymentProcessorFactoryImpl paymentProcessorFactory;
    private final NotificationService notificationService;
    private final CoursePrototype coursePrototype;

    public LearningPlatformFacade() {
        this(new CourseRepositoryProxy(new InMemoryCourseRepository()));
    }

    public LearningPlatformFacade(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        this.courseService = new CourseService(courseRepository);
        this.discountPolicyFactory = new DiscountPolicyFactoryImpl();
        this.paymentProcessorFactory = new PaymentProcessorFactoryImpl();
        this.notificationService = new NotificationServiceImpl();
        this.coursePrototype = new CoursePrototype();
    }

    /**
     * Creates a new course via the builder and publishes it into the repository.
     */
    public Course publishCourse(String title, String description, double price, String instructorId) {
        CourseBuilder builder = new CourseBuilder();
        Course course = builder
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setInstructorId(instructorId)
                .build();
        courseService.addCourse(course);
        return course;
    }

    /**
     * Clones an existing course with a new price.
     */
    public Course duplicateCourse(Course source, double newPrice) {
        Course cloned = coursePrototype.cloneCourse(source);
        cloned.setPrice(newPrice);
        courseService.addCourse(cloned);
        return cloned;
    }

    /**
     * Enrolls a student with selected discount and payment processor.
     */
    public void enrollStudent(Student student, String courseId, String discountType, String processorType) {
        Course course = courseRepository.getCourseById(courseId);
        if (course == null) {
            LoggerSingleton.getInstance().log("Course not found for enrollment: " + courseId);
            return;
        }

        DiscountPolicy discountPolicy = discountPolicyFactory.createDiscountPolicy(discountType);
        PaymentProcessor paymentProcessor = paymentProcessorFactory.createPaymentProcessor(processorType);
        EnrollmentService enrollmentService = new EnrollmentService(
                courseRepository,
                paymentProcessor,
                notificationService,
                discountPolicy
        );
        double discountedPrice = discountPolicy.applyDiscount(course.getPrice());

        EnrollmentRequest request = new EnrollmentRequest(student, course, discountedPrice);
        EnrollmentHandler approvalChain = new EnrollmentApprovalChainBuilder().buildDefault();
        approvalChain.handle(request);

        if (request.isRejected()) {
            LoggerSingleton.getInstance().log("Enrollment rejected: " + request.getRejectionReason());
            return;
        }

        enrollmentService.enrollStudentWithPrice(student, course, request.getPriceToCharge());
    }
}
