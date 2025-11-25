package com.solid.onlinelearning.services.chain;

import com.solid.onlinelearning.services.LoggerSingleton;

/**
 * Lightweight fraud flag that rejects obviously suspicious student names/emails.
 */
public class FraudFlagHandler extends BaseEnrollmentHandler {
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    @Override
    protected void process(EnrollmentRequest request) {
        String email = request.getStudent().getEmail();
        if (email != null && email.toLowerCase().contains("fraud")) {
            request.reject("Fraudulent email pattern detected");
            logger.log("[Chain] Reject enrollment: fraud pattern in " + email);
        }
    }
}
