package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;


public class PaymentServiceImpl implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[PAYMENT] Processed payment of $" + amount);
        return true; // эмуляция успешного платежа
    }
}
