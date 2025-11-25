package com.solid.onlinelearning.services.chain;

/**
 * Handler contract for enrollment checks.
 */
public interface EnrollmentHandler {
    EnrollmentHandler setNext(EnrollmentHandler next);
    void handle(EnrollmentRequest request);
}
