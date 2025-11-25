package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

/**
 * Decorator that logs payment attempts and their outcomes.
 */
public class LoggingPaymentDecorator extends PaymentProcessorDecorator {

    public LoggingPaymentDecorator(PaymentProcessor delegate) {
        super(delegate);
    }

    @Override
    public boolean processPayment(double amount) {
        LoggerSingleton.getInstance().log("Trying to process payment of $" + amount);
        boolean result = super.processPayment(amount);
        LoggerSingleton.getInstance().log("Payment " + (result ? "succeeded" : "failed") + " for $" + amount);
        return result;
    }
}
