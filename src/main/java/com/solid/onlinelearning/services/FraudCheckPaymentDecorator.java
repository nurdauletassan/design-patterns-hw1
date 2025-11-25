package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

/**
 * Decorator that blocks suspiciously large payments.
 */
public class FraudCheckPaymentDecorator extends PaymentProcessorDecorator {
    private final double maxAmountWithoutReview;

    public FraudCheckPaymentDecorator(PaymentProcessor delegate, double maxAmountWithoutReview) {
        super(delegate);
        this.maxAmountWithoutReview = maxAmountWithoutReview;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount > maxAmountWithoutReview) {
            LoggerSingleton.getInstance().log(
                    "Fraud check failed: $" + amount + " exceeds automated limit of $" + maxAmountWithoutReview);
            return false;
        }
        return super.processPayment(amount);
    }
}
