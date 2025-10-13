package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.DiscountPolicy;


public class StudentDiscountPolicy implements DiscountPolicy {

    @Override
    public double applyDiscount(double price) {
        return price * 0.9; // 10% скидка
    }
}
