package com.solid.onlinelearning.services.chain;

import com.solid.onlinelearning.services.LoggerSingleton;

/**
 * Validates presence of student contact info.
 */
public class EmailValidationHandler extends BaseEnrollmentHandler {
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    @Override
    protected void process(EnrollmentRequest request) {
        if (request.getStudent().getEmail() == null || !request.getStudent().getEmail().contains("@")) {
            request.reject("Invalid student email");
            logger.log("[Chain] Reject enrollment: invalid email");
        }
    }
}
