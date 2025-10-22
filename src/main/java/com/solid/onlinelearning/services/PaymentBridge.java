package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

public class PaymentBridge {
    private PaymentProcessor paymentProcessor;

    public PaymentBridge(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void executePayment(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
