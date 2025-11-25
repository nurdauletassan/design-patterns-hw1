package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;
import java.util.Objects;

/**
 * Base decorator for PaymentProcessor to allow stacking cross-cutting behavior.
 */
public abstract class PaymentProcessorDecorator implements PaymentProcessor {
    protected final PaymentProcessor delegate;

    protected PaymentProcessorDecorator(PaymentProcessor delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    @Override
    public boolean processPayment(double amount) {
        return delegate.processPayment(amount);
    }
}
