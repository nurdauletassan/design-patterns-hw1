package com.solid.onlinelearning.services.chain;

import com.solid.onlinelearning.models.Course;
import com.solid.onlinelearning.models.Student;

/**
 * Request object shared across the chain; contains extrinsic data needed by handlers.
 */
public class EnrollmentRequest {
    private final Student student;
    private final Course course;
    private double priceToCharge;
    private boolean rejected;
    private String rejectionReason;

    public EnrollmentRequest(Student student, Course course, double priceToCharge) {
        this.student = student;
        this.course = course;
        this.priceToCharge = priceToCharge;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getPriceToCharge() {
        return priceToCharge;
    }

    public void adjustPrice(double priceToCharge) {
        this.priceToCharge = priceToCharge;
    }

    public boolean isRejected() {
        return rejected;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void reject(String reason) {
        this.rejected = true;
        this.rejectionReason = reason;
    }
}
