package com.solid.onlinelearning.models;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

public class StripePaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
        return true;
    }
}
