
package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.PaymentProcessor;
import com.solid.onlinelearning.models.StripePaymentProcessor;
import com.solid.onlinelearning.models.PayPalPaymentProcessor;

/**
 * PaymentProcessorFactoryImpl — фабрика для создания платёжных процессоров.
 * Реализует паттерн Factory Method.
 */
public class PaymentProcessorFactoryImpl implements PaymentProcessorFactory {

    /**
     * Метод для создания платёжного процессора в зависимости от типа.
     * @param processorType Тип платёжного процессора (например, "Stripe", "PayPal")
     * @return Возвращает экземпляр платёжного процессора
     */
    @Override
    public PaymentProcessor createPaymentProcessor(String processorType) {
        if (processorType.equalsIgnoreCase("Stripe")) {
            return new StripePaymentProcessor();
        } else if (processorType.equalsIgnoreCase("PayPal")) {
            return new PayPalPaymentProcessor();
        }
        throw new IllegalArgumentException("Unknown payment processor type: " + processorType);
    }
}
