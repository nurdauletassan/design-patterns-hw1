package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;
import com.solid.onlinelearning.models.StripePaymentProcessor;
import com.solid.onlinelearning.models.PayPalPaymentProcessor;


public class PaymentProcessorAdapter implements PaymentProcessor {
    private StripePaymentProcessor stripeProcessor;
    private PayPalPaymentProcessor paypalProcessor;

    public PaymentProcessorAdapter(StripePaymentProcessor stripeProcessor) {
        this.stripeProcessor = stripeProcessor;
    }

    public PaymentProcessorAdapter(PayPalPaymentProcessor paypalProcessor) {
        this.paypalProcessor = paypalProcessor;
    }

    @Override
    public void processPayment(double amount) {
        if (stripeProcessor != null) {
            stripeProcessor.processStripePayment(amount);
        } else if (paypalProcessor != null) {
            paypalProcessor.processPayPalPayment(amount);
        }
    }
}
