package com.solid.onlinelearning.models;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

public class PayPalPaymentProcessor implements PaymentProcessor {

    // Метод для обработки платежа через PayPal
    public void processPayPalPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using PayPal.");
    }

    @Override
    public boolean processPayment(double amount) {
        processPayPalPayment(amount);
        return true;
    }
}
