package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

public class PaymentProcessorFactory {

    // Метод фабрики для создания платёжных процессоров
    public static PaymentProcessor createPaymentProcessor(String type) {
        if (type.equals("CreditCard")) {
            return new CreditCardPaymentProcessor();
        } else if (type.equals("PayPal")) {
            return new PayPalPaymentProcessor();
        } else if (type.equals("Stripe")) {
            return new StripePaymentProcessor();
        }
        throw new IllegalArgumentException("Unknown payment processor type");
    }
}
