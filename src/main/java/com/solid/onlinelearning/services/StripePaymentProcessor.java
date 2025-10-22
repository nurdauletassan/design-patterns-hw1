package com.solid.onlinelearning.models;

public class StripePaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
        // Реализация логики обработки платежа через Stripe
    }
}
