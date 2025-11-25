package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;

/**
 * PaymentProcessorFactory определяет контракт фабрики для создания процессоров оплат.
 */
public interface PaymentProcessorFactory {
    PaymentProcessor createPaymentProcessor(String type);
}
