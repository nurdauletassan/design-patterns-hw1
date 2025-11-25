package com.solid.onlinelearning.services.chain;

import com.solid.onlinelearning.services.LoggerSingleton;

/**
 * Rejects suspiciously large payments to reduce fraud.
 */
public class PaymentLimitHandler extends BaseEnrollmentHandler {
    private final double limit;
    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    public PaymentLimitHandler(double limit) {
        this.limit = limit;
    }

    @Override
    protected void process(EnrollmentRequest request) {
        if (request.getPriceToCharge() > limit) {
            request.reject("Price exceeds approval limit of $" + limit);
            logger.log("[Chain] Reject enrollment: price " + request.getPriceToCharge() + " over limit " + limit);
        }
    }
}
